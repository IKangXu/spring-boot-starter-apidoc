/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.annotation;

import cn.ikangxu.boot.apidoc.common.RequestHandlerProvider;
import cn.ikangxu.boot.apidoc.common.registrar.ImportDocRegistrar;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * @className EnableDoc
 * @description TODO
 * @author kangxu [xukang@engine3d.com]
 * @date 2019/11/8 16:56
 * @version v1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DependsOn({"requestHandlerProvider"})
public @interface EnableDoc {
}
