/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common.registrar;

import cn.ikangxu.boot.apidoc.annotation.EnableDoc;
import cn.ikangxu.boot.apidoc.common.PropertiesConst;
import cn.ikangxu.boot.apidoc.common.RequestHandlerProvider;
import cn.ikangxu.boot.apidoc.util.SpringContextUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className ImportDocRegistrar
 * @description
 * @date 2019/11/8 16:58
 */
public class ImportDocRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(EnableDoc.class.getName(), false));
        if (null != annotationAttributes) {
            BeanDefinitionBuilder springContextUtilsBean = BeanDefinitionBuilder.genericBeanDefinition(SpringContextUtils.class);
            beanDefinitionRegistry.registerBeanDefinition("springContextUtils", springContextUtilsBean.getRawBeanDefinition());

            BeanDefinitionBuilder propertiesConstBean = BeanDefinitionBuilder.genericBeanDefinition(PropertiesConst.class);
            propertiesConstBean.addDependsOn("springContextUtils");
            beanDefinitionRegistry.registerBeanDefinition("propertiesConst", propertiesConstBean.getRawBeanDefinition());

            BeanDefinitionBuilder requestHandlerProviderBean = BeanDefinitionBuilder.genericBeanDefinition(RequestHandlerProvider.class);
            requestHandlerProviderBean.addDependsOn("springContextUtils");
            requestHandlerProviderBean.addDependsOn("propertiesConst");
            beanDefinitionRegistry.registerBeanDefinition("requestHandlerProvider", requestHandlerProviderBean.getRawBeanDefinition());
        }
    }
}
