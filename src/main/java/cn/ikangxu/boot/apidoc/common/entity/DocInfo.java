/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common.entity;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @className DocInfo
 * @description 
 * @author kangxu [xukang@engine3d.com]
 * @date 2019/11/8 12:55
 * @version v1.0
 */
@Data
@Builder
public class DocInfo {
    private String version;
    private String title;
    private String description;
}
