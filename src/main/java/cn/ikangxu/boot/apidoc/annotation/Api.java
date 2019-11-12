/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.annotation;

import java.lang.annotation.*;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className Api
 * @description
 * @date 2019/11/8 10:38
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

    // 分组
    String group() default "default";
}
