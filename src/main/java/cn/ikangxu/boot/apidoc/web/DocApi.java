/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.web;

import cn.ikangxu.boot.apidoc.annotation.Api;
import cn.ikangxu.boot.apidoc.annotation.ApiIgnore;
import cn.ikangxu.boot.apidoc.common.Json;
import cn.ikangxu.boot.apidoc.common.entity.Docket;
import cn.ikangxu.boot.apidoc.util.ObjectUtils;
import cn.ikangxu.boot.apidoc.util.SpringContextUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className DocApi
 * @description
 * @date 2019/11/8 11:01
 */
//@ApiIgnore
@Api(name = "文档", sort = 1)
@RequestMapping("test/api")
@RestController
public class DocApi {

    @GetMapping(value = "v1/api-doc")
    public ResponseEntity<Json> getDocumentation(@RequestParam(value = "group", required = false) String docGroup) {
        // String groupName = ObjectUtils.isNotEmpty(docGroup) ? docGroup : "default";
        Map<String, Docket> docketMap = SpringContextUtils.getBeansOfType(Docket.class);

        System.out.println(docketMap);

        return new ResponseEntity(null, HttpStatus.OK);
    }

}
