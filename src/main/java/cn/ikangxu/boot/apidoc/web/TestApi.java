/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.web;

import cn.ikangxu.boot.apidoc.annotation.*;
import cn.ikangxu.boot.apidoc.common.HttpMethod;
import cn.ikangxu.boot.apidoc.common.ParamType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @className TestApi
 * @description 
 * @author kangxu [xukang@engine3d.com]
 * @date 2019/11/12 17:29
 * @version v1.0
 */
@Api(name = "测试", sort = 1)
@RestController
@RequestMapping("/api/test")
public class TestApi {

    @ApiMethod(
            name = "测试接口3-test",
            method = HttpMethod.GET,
            response = Void.class,
            description = "",
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
    public String test(String testParam1, String testParam2) {
        return "test3";
    }

}
