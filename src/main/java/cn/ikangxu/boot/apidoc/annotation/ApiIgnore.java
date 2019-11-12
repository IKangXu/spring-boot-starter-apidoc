/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.annotation;

import java.lang.annotation.*;

/**
 *
 * @className ApiIgnore
 * @description 
 * @author kangxu [xukang@engine3d.com]
 * @date 2019/11/8 10:39
 * @version v1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Documented
public @interface ApiIgnore {

}
