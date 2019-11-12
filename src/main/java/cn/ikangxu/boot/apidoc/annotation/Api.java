/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.annotation;

import java.lang.annotation.*;

/**
 *
 * @className Api
 * @description 
 * @author kangxu [xukang@engine3d.com]
 * @date 2019/11/8 10:38
 * @version v1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Api {
    // 中文名称 （默认取类名）
    String name() default "";

    // 描述
    String description() default "";

    int sort();
}
