/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common;

import cn.ikangxu.boot.apidoc.util.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.*;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className RequestHandlerProvider
 * @description
 * @date 2019/11/8 17:00
 */
@Component("requestHandlerProvider")
@DependsOn({"springContextUtils"})
public class RequestHandlerProvider {

    @Bean
    public List<RequestHandler> requestHandlers() {
        Map<String, RequestMappingInfoHandlerMapping> beans = SpringContextUtils.getBeansOfType(RequestMappingInfoHandlerMapping.class);

        // 获取handlerMappings类
        Set<Map.Entry<RequestMappingInfo, HandlerMethod>> entries = new HashSet<>();
        for (Map.Entry<String, RequestMappingInfoHandlerMapping> entry : beans.entrySet()) {
            RequestMappingInfoHandlerMapping handlerMapping = entry.getValue();

            entries.addAll(handlerMapping.getHandlerMethods().entrySet());
        }

        List<RequestHandler> requestHandlers = new ArrayList<>();
        Iterator<Map.Entry<RequestMappingInfo, HandlerMethod>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<RequestMappingInfo, HandlerMethod> next = iterator.next();

            RequestHandler requestHandler = new RequestHandler();
            requestHandler.setRequestMapping(next.getKey());
            requestHandler.setHandlerMethod(next.getValue());

            requestHandlers.add(requestHandler);
        }
        return requestHandlers;
    }
}
