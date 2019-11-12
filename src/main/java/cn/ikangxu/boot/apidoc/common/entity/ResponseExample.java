/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common.entity;

import lombok.Data;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className ResponseExample
 * @description
 * @date 2019/11/11 13:23
 */
@Data
public class ResponseExample {
    // 成功示例
    private Object success;
    // 失败示例
    private Object fail;

    // 字段信息
    private Object info;
}
