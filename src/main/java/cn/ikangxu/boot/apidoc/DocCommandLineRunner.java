/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc;

import cn.ikangxu.boot.apidoc.common.RequestHandler;
import cn.ikangxu.boot.apidoc.util.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.actuate.endpoint.web.servlet.ControllerEndpointHandlerMapping;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className DocCommandLineRunner
 * @description
 * @date 2019/11/8 15:02
 */
public class DocCommandLineRunner implements CommandLineRunner {
    @Autowired
    List<RequestMappingInfoHandlerMapping> handlerMappings;

    @Override
    public void run(String... args) throws Exception {
        // 获取handlerMappings类
        Set<Map.Entry<RequestMappingInfo, HandlerMethod>> entries = new HashSet<>();
        for (RequestMappingInfoHandlerMapping handlerMapping : handlerMappings) {
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

        System.out.println(entries);
    }
}
