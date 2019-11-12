/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.config;

import cn.ikangxu.boot.apidoc.annotation.*;
import cn.ikangxu.boot.apidoc.common.Documentation;
import cn.ikangxu.boot.apidoc.common.RequestHandler;
import cn.ikangxu.boot.apidoc.common.entity.*;
import cn.ikangxu.boot.apidoc.util.SpringContextUtils;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Predicates.and;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className DocConfig
 * @description
 * @date 2019/11/8 17:09
 */
@Configuration
@EnableDoc
public class ApiDocConfig {

    @Autowired
    private List<RequestHandler> requestHandlers;

    @Bean
    @Order(1)
    public Docket test1() {

        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.declaringClass();
                // 被注解的类
                if (declaringClass.isAnnotationPresent(RestController.class)
                        || declaringClass.isAnnotationPresent(Controller.class)) {

                    // 被注解的方法
                    if (input.isAnnotatedWith(GetMapping.class) || input.isAnnotatedWith(PostMapping.class)
                            || input.isAnnotatedWith(RequestMapping.class)
                            || declaringClass.isAnnotationPresent(ApiIgnore.class))
                        return true;
                }
                return false;
            }
        };

        List<RequestHandler> newArrayList = Lists.newArrayList(Iterables.filter(requestHandlers, predicate));

        return Docket.builder().docInfo(DocInfo.builder().title("test").description("描述").version("v1.0.0").build()).apis(newArrayList).isDebuger(true).enabled(true).groupName("test").build();
    }

    @Bean
    @Order(2)
    public Map<String, Documentation> documentationCache() {
        Map<String, Docket> docketMap = SpringContextUtils.getBeansOfType(Docket.class);

        List<Documentation> documentations = new ArrayList<>();

        for (Map.Entry<String, Docket> entry : docketMap.entrySet()) {
            Docket docket = entry.getValue();

            Documentation documentation = new Documentation();

            documentation.setDocInfo(docket.getDocInfo());
            documentation.setGroupName(docket.getGroupName());
            documentation.setEnabled(docket.isEnabled());
            documentation.setDebuger(docket.isDebuger());

            List<RequestHandler> apis = docket.getApis();
            List<Tab> tabs = new ArrayList<>();
            for (RequestHandler handler : apis) {

                ApiIgnore apiIgnore = handler.getBeanAnnotation(ApiIgnore.class);
                if (null != apiIgnore) {
                    continue;
                }

                Api api = handler.getBeanAnnotation(Api.class);
                if (null == api) {
                    continue;
                }

                Tab tab = new Tab();
                tab.setName(api.name());
                tab.setSort(api.sort());
                tab.setDescription(api.description());

                String url = handler.getRequestMapping().getPatternsCondition().getPatterns().toArray()[0].toString();
                String method = handler.getRequestMapping().getMethodsCondition().getMethods().toArray()[0].toString();
                Path path = new Path();
                path.setUrl(url);
                path.setMethod(method);

                ApiMethod apiMethod = handler.getAnnotation(ApiMethod.class);

                path.setDescription(apiMethod.description());
                path.setName(apiMethod.name());

                Object[] produces = handler.getRequestMapping().getProducesCondition().getProducibleMediaTypes().toArray();
                path.setProduces(produces);

                List<Parameter> parameters = new ArrayList<>();

                ApiParams apiParams = handler.getAnnotation(ApiParams.class);
                if (null != apiParams) {
                    for (ApiParam apiParam : apiParams.value()) {
                        parameters.add(buildParameter(apiParam));
                    }
                }

                ApiParam apiParam = handler.getAnnotation(ApiParam.class);
                if (null != apiParam) {
                    parameters.add(buildParameter(apiParam));
                }

                path.setParameters(parameters);

                // path.setProduces();
                List<Response> responses = new ArrayList<>();

                ApiResponses apiResponses = handler.getAnnotation(ApiResponses.class);
                if (null != apiResponses) {
                    for (ApiResponse apiResponse : apiResponses.value()) {
                        responses.add(buildResponse(apiResponse));
                    }
                }

                ApiResponse apiResponse = handler.getAnnotation(ApiResponse.class);
                if (null != apiResponse) {
                    responses.add(buildResponse(apiResponse));
                }

                path.setResponses(responses);

                tab.setPaths(null);

                tabs.add(tab);
            }
            documentation.setTabs(tabs);

            documentations.add(documentation);
        }

        return null;
    }

    private Parameter buildParameter(ApiParam apiParam) {
        return Parameter.builder().name(apiParam.name())
                .description(apiParam.description())
                .dataType(apiParam.dataType())
                .paramType(apiParam.paramType())
                .required(apiParam.required())
                .allowMultiple(apiParam.allowMultiple())
                .example(apiParam.example()).build();
    }

    private Response buildResponse(ApiResponse apiResponse) {
        return Response.builder().code(apiResponse.code()).description(apiResponse.description()).msg(apiResponse.msg()).build();
    }

}
