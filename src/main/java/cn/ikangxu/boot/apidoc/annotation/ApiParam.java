/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.annotation;

import cn.ikangxu.boot.apidoc.common.ParamType;

import java.lang.annotation.*;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className ApiParam
 * @description TODO
 * @date 2019/11/8 10:40
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiParam {

    // 参数名
    String name();

    // 描述
    String description();

    // 传参方式
    ParamType paramType();

    // 参数类型
    String dataType();

    // 是否必传
    boolean required() default false;

    String example() default "";

    boolean allowMultiple() default false;

}
