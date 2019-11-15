/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.annotation;

import cn.ikangxu.boot.apidoc.common.HttpMethod;

import java.lang.annotation.*;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className ApiMethod
 * @description TODO
 * @date 2019/11/8 10:40
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiMethod {
    // 中文名称 （默认取方法名）
    String name() default "";

    // 方法类型
    HttpMethod[] method();

    // 返回值
    Class<?> response() default Void.class;

    // 是否需要登录
    // boolean isLogin() default false;

    // 参数中是否包含文件
    // boolean isParamContainsFile() default false;

    String description() default "";

    // 是否废弃使用
    boolean discarded() default false;
}
