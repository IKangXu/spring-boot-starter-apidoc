/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.web;

import cn.ikangxu.boot.apidoc.annotation.Api;
import cn.ikangxu.boot.apidoc.annotation.ApiIgnore;
import cn.ikangxu.boot.apidoc.common.Documentation;
import cn.ikangxu.boot.apidoc.common.entity.Docket;
import cn.ikangxu.boot.apidoc.util.ObjectUtils;
import cn.ikangxu.boot.apidoc.util.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className DocApi
 * @description
 * @date 2019/11/8 11:01
 */
@RestController
public class DocApi {

    @Autowired
    private Map<String, Documentation> documentationCache;

    @GetMapping(value = "v1/api-doc")
    public ResponseEntity getDocumentation(@RequestParam(value = "group", required = false) String docGroup) {
        return new ResponseEntity(documentationCache, HttpStatus.OK);
    }

}
