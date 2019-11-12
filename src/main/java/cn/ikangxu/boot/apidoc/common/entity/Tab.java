/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common.entity;

import lombok.Data;

import java.util.List;

/**
 *
 * @className Tab
 * @description 
 * @author kangxu [xukang@engine3d.com]
 * @date 2019/11/11 13:14
 * @version v1.0
 */
@Data
public class Tab {

    private String name;
    private String description;
    private int sort;
    private List<Path> paths;
}
