/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common.entity;

import lombok.Data;

import java.util.List;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className Path
 * @description
 * @date 2019/11/11 13:15
 */
@Data
public class Path {
    // url
    private Object[] url;
    // 名称
    private String name;
    // 描述
    private String description;
    // 方法类型  get post 等
    private Object[] method;
    // 响应接受类型
    private Object[] produces;
    // 响应类型
    private List<Response> responses;
    // 响应示例
    private ResponseExample responseExample;
    // 参数
    private List<Parameter> parameters;

    private boolean discarded;

    private String id;
}
