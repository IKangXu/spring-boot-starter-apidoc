/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.web;

import cn.ikangxu.boot.apidoc.annotation.*;
import cn.ikangxu.boot.apidoc.common.HttpMethod;
import cn.ikangxu.boot.apidoc.common.ParamType;
import cn.ikangxu.boot.apidoc.common.test.TestEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className TestApi
 * @description
 * @date 2019/11/12 17:29
 */
@Api(name = "测试1", sort = 1)
@RestController
@RequestMapping("/api/test1")
public class Test1Api {

    @ApiMethod(
            name = "测试接口3-test1",
            method = HttpMethod.GET,
            response = TestEntity.class,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            description = "这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等这是一个测试接口，编写文档，新一代文档接口，方便开发人员联调和对外提供等等",
            discarded = false
    )
    @ApiParams({
            @ApiParam(name = "testParam1", description = "测试参数1", paramType = ParamType.QUERY, dataType = "string", required = true, allowMultiple = false, example = "测试参数示例1"),
            @ApiParam(name = "testParam2", description = "测试参数2", paramType = ParamType.QUERY, dataType = "string", required = true, allowMultiple = false, example = "测试参数示例2")
    })
    @ApiResponses({
            @ApiResponse(code = 200, msg = "成功", description = ""),
            @ApiResponse(code = 500, msg = "错误", description = "")
    })
    @GetMapping(value = "test3", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String test1(String testParam1, String testParam2) {
        return "test3";
    }

}
