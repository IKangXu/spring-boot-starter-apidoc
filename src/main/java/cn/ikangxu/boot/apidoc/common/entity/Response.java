/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common.entity;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @className Response
 * @description 
 * @author kangxu [xukang@engine3d.com]
 * @date 2019/11/11 13:17
 * @version v1.0
 */
@Data
@Builder
public class Response {

    private int code;
    private String msg;
    private String description;

}
