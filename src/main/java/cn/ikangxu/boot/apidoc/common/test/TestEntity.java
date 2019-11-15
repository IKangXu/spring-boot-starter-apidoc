/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common.test;

import cn.ikangxu.boot.apidoc.annotation.ApiResponseField;
import lombok.Data;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className TestEntity
 * @description
 * @date 2019/11/14 17:01
 */
@Data
public class TestEntity {

    @ApiResponseField(name = "唯一标识", example = "sdf-45345-dfgfg454-4556")
    private String id;
    @ApiResponseField(name = "名称", example = "kangxu")
    private String name;
}
