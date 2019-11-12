/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common.entity;

import cn.ikangxu.boot.apidoc.common.ParamType;
import lombok.Builder;
import lombok.Data;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className Parameter
 * @description
 * @date 2019/11/8 13:02
 */
@Data
@Builder
public class Parameter {
    private String name;
    private String description;
    private ParamType paramType;
    private String dataType;
    private boolean required;
    private Object example;
    private boolean allowMultiple;
}
