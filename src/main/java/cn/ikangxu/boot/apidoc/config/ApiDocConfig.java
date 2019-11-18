/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.config;

import cn.ikangxu.boot.apidoc.annotation.*;
import cn.ikangxu.boot.apidoc.common.*;
import cn.ikangxu.boot.apidoc.common.entity.*;
import cn.ikangxu.boot.apidoc.common.test.TestEntity;
import cn.ikangxu.boot.apidoc.util.ListUtils;
import cn.ikangxu.boot.apidoc.util.ObjectUtils;
import cn.ikangxu.boot.apidoc.util.SpringContextUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

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
@DependsOn({"springContextUtils", "propertiesConst"})
public class ApiDocConfig {

    @Autowired
    private List<RequestHandler> requestHandlers;
    @Autowired
    private ObjectMapper objectMapper;

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
    public Map<String, Documentation> documentationCache() throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        Map<String, Docket> docketMap = SpringContextUtils.getBeansOfType(Docket.class);

        Map<String, Documentation> documentations = new HashMap<>();

        for (Map.Entry<String, Docket> entry : docketMap.entrySet()) {
            Docket docket = entry.getValue();

            List<RequestHandler> apis = docket.getApis();
            if (ListUtils.isEmpty(apis)) {
                continue;
            }

            Map<String, Tab> tabMap = new HashMap<>();

            for (RequestHandler handler : apis) {

                ApiIgnore apiIgnore = handler.getBeanAnnotation(ApiIgnore.class);
                if (null != apiIgnore) {
                    continue;
                }

                Api api = handler.getBeanAnnotation(Api.class);
                if (null == api) {
                    continue;
                }

                Documentation documentation = null;
                if (ObjectUtils.isNotEmpty(documentations.get(api.group()))) {
                    documentation = documentations.get(api.group());
                } else {
                    documentation = new Documentation();
                }

                documentation.setDocInfo(docket.getDocInfo());
                documentation.setGroupName(api.group());
                documentation.setEnabled(docket.isEnabled());
                documentation.setDebuger(docket.isDebuger());

                Tab tab = new Tab();
                tab.setName(api.name());
                tab.setSort(api.sort());
                tab.setDescription(api.description());

                String contextPath = ((AnnotationConfigServletWebServerApplicationContext) SpringContextUtils.applicationContext).getServletContext().getContextPath();

                List<Object> urls = new ArrayList<>();
                Object[] urlObjs = handler.getRequestMapping().getPatternsCondition().getPatterns().toArray();
                for (Object urlObj : urlObjs) {
                    String url = "";
                    if (!contextPath.equals("/")) {
                        url += contextPath;
                    }
                    url += urlObj;

                    urls.add(url);
                }

                Object[] methods = handler.getRequestMapping().getMethodsCondition().getMethods().toArray();
                List<Object> methodObjs = new ArrayList<>();
                if (methods.length == 0) {
                    HttpMethod[] values = HttpMethod.values();
                    for (HttpMethod httpMethod : values) {
                        methodObjs.add(httpMethod.name());
                    }
                    methods = methodObjs.toArray();
                }
                Path path = new Path();
                path.setUrl(urls.toArray());
                path.setMethod(methods);

                StringBuilder id = new StringBuilder(api.group());
                id.append("_");
                id.append(handler.getHandlerMethod().getBean());
                id.append("_");
                id.append(handler.getHandlerMethod().getMethod().getName());
                path.setId(id.toString());

                ApiMethod apiMethod = handler.getAnnotation(ApiMethod.class);
                if (null == apiMethod) {
                    tab.setPaths(new ArrayList<>());
                    tabMap.put(api.name(), tab);
                    continue;
                }


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

                ResponseExample responseExample = new ResponseExample();
                Class<?> response = apiMethod.response();
                List<Map<String, Object>> fieldInfo = new ArrayList<>();
                if (!"java.lang.Void".equals(response.getName())) {
                    Map<String, Object> examples = new HashMap<>();
                    Field[] fields = response.getDeclaredFields();
                    for (Field field : fields) {
                        Annotation[] annotations = field.getAnnotations();
                        for (Annotation annotation : annotations) {
                            if (annotation instanceof ApiResponseField) {
                                Map<String, Object> fieldJ = new HashMap();
                                fieldJ.put("description", ((ApiResponseField) annotation).name());
                                String example = ((ApiResponseField) annotation).example();
                                fieldJ.put("type", field.getType().getSimpleName());
                                String name = field.getName();
                                fieldJ.put("field", name);
                                fieldJ.put("example", example);
                                fieldInfo.add(fieldJ);

                                examples.put(name, example);
                            } else {
                                continue;
                            }
                        }
                    }

                    responseExample.setFail(buildSuccessByType(PropertiesConst.RESPONSE_TEMPLATE_FAILURE_TEMPLATE.toString(), null, PropertiesConst.RESPONSE_TEMPLATE_FAILURE_TYPE));
                    responseExample.setSuccess(buildSuccessByType(PropertiesConst.RESPONSE_TEMPLATE_SUCCESS_TEMPLATE.toString(), examples, PropertiesConst.RESPONSE_TEMPLATE_SUCCESS_TYPE));
                    responseExample.setInfo(fieldInfo);
                    path.setResponseExample(responseExample);
                }

                path.setDiscarded(apiMethod.discarded());

                if (ObjectUtils.isNotEmpty(tabMap.get(tab.getName()))) {
                    List<Path> paths = tabMap.get(tab.getName()).getPaths();
                    paths.add(path);

                    tab.setPaths(paths);
                } else {
                    List<Path> paths = new ArrayList<>();
                    paths.add(path);
                    tab.setPaths(paths);
                }

                tabMap.put(api.name(), tab);

                documentation.getTabs().clear();
                documentation.getTabs().addAll(tabMap.values());

                documentations.put(api.group(), documentation);
            }
        }

        return documentations;
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

    private Object buildSuccess(String template, Object object) throws JsonProcessingException {
        if (!ObjectUtils.isNotEmpty(object)) {
            return template;
        }
        String param = objectMapper.writeValueAsString(object);
        if (!template.contains("{{DATA}}")) {
            return param;
        }
        return (Object) template.replace("{{DATA}}", param);
    }

    private Object buildSuccessByType(String template, Object object, Object type) throws IOException {
        String response = buildSuccess(template, object).toString();

        if (((String) type).toUpperCase().equals(TemplateType.JSON.name())) {
            return objectMapper.readValue(response, objectMapper.getTypeFactory().constructParametricType(HashMap.class, Object.class, Object.class));
        }
        if (((String) type).toUpperCase().equals(TemplateType.TEXT.name())) {
            return response;
        }
        return "";
    }
}
