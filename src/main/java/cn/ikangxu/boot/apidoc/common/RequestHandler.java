/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common;

import lombok.Data;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.lang.annotation.Annotation;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className WebMvcRequestHandler
 * @description
 * @date 2019/11/8 16:40
 */
@Data
public class RequestHandler {

    private RequestMappingInfo requestMapping;
    private HandlerMethod handlerMethod;

    public Class<?> declaringClass() {
        return handlerMethod.getBeanType();
    }

    public boolean isAnnotatedWith(Class<? extends Annotation> annotation) {
        return null != AnnotationUtils.findAnnotation(handlerMethod.getMethod(), annotation);
    }

    public <T> T getAnnotation(Class<? extends Annotation> annotation) {
        return (T) AnnotationUtils.findAnnotation(handlerMethod.getMethod(), annotation);
    }

    public Class<?> getBeanType() {
        return handlerMethod.getBeanType();
    }

    public <T> T getBeanAnnotation(Class<? extends Annotation> annotation) {
        return (T) getBeanType().getAnnotation(annotation);
    }
}
