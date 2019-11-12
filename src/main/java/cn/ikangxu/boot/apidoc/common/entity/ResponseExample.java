/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common.entity;

import lombok.Data;

/**
 *
 * @className ResponseExample
 * @description 
 * @author kangxu [xukang@engine3d.com]
 * @date 2019/11/11 13:23
 * @version v1.0
 */
@Data
public class ResponseExample {
    // 成功示例
    private Object success;
    // 失败示例
    private Object fail;
}
