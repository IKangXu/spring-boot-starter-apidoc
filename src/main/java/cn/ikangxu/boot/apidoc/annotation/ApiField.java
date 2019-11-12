/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.annotation;

import java.lang.annotation.*;

/**
 *
 * @className ApiField
 * @description 
 * @author kangxu [xukang@engine3d.com]
 * @date 2019/11/8 10:38
 * @version v1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiField {
    // 返回结果
    // 正确的返回结果
    // 错误的返回结果
    String name();
}
