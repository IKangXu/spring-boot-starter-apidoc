/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.annotation;

import java.lang.annotation.*;

/**
 *
 * @className ApiParams
 * @description TODO
 * @author kangxu [xukang@engine3d.com]
 * @date 2019/11/8 10:41
 * @version v1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiParams {
    ApiParam[] value();
}
