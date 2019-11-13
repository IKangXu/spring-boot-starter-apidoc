/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className Json
 * @description
 * @date 2019/11/13 8:47
 */
@Data
public class Json {
    public static <T> Map<String, Object> success(T t) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", t);
        result.put("msg", "success");
        return result;
    }

    public static <T> Map<String, Object> failure(T t) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("msg", "failure");
        return result;
    }
}
