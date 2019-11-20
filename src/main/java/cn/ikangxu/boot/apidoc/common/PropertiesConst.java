/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common;

import cn.ikangxu.boot.apidoc.util.ObjectUtils;
import cn.ikangxu.boot.apidoc.util.SpringContextUtils;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className PropertiesConst
 * @description
 * @date 2019/11/14 17:27
 */
public class PropertiesConst {
    // 成功返回模板，默认返回success，可以定义模板，语法支持{{DATA}}   可以定义模板为{"code":200,"msg":"success","data":{{DATA}}}，会根据自己自定义的模板进行返回值示例的构造
    public static Object RESPONSE_TEMPLATE_SUCCESS_TEMPLATE = SpringContextUtils.getProperty("cn.ikangxu.doc.response.success.template");
    public static Object RESPONSE_TEMPLATE_SUCCESS_TYPE = SpringContextUtils.getProperty("cn.ikangxu.doc.response.success.type");
    // 失败返回模板，默认返回failure
    public static Object RESPONSE_TEMPLATE_FAILURE_TEMPLATE = SpringContextUtils.getProperty("cn.ikangxu.doc.response.fail.template");
    public static Object RESPONSE_TEMPLATE_FAILURE_TYPE = SpringContextUtils.getProperty("cn.ikangxu.doc.response.fail.type");

    // 全局启用参数
    public static Object GLOBAL_ENABLED = SpringContextUtils.getProperty("cn.ikangxu.doc.global.enabled");
    public static Object DOMAIN = SpringContextUtils.getProperty("cn.ikangxu.doc.global.domain");

    static {
        if (!ObjectUtils.isNotEmpty(RESPONSE_TEMPLATE_SUCCESS_TEMPLATE)) {
            RESPONSE_TEMPLATE_SUCCESS_TEMPLATE = "{\"code\":200,\"msg\":\"success\",\"data\":{{DATA}}}";
        }
        if (!ObjectUtils.isNotEmpty(RESPONSE_TEMPLATE_SUCCESS_TYPE)) {
            RESPONSE_TEMPLATE_SUCCESS_TYPE = TemplateType.JSON.name().toLowerCase();
        }
        if (!ObjectUtils.isNotEmpty(RESPONSE_TEMPLATE_FAILURE_TEMPLATE)) {
            RESPONSE_TEMPLATE_FAILURE_TEMPLATE = "failure";
        }
        if (!ObjectUtils.isNotEmpty(RESPONSE_TEMPLATE_FAILURE_TYPE)) {
            RESPONSE_TEMPLATE_FAILURE_TYPE = TemplateType.TEXT.name().toLowerCase();
        }
        if (!ObjectUtils.isNotEmpty(GLOBAL_ENABLED)) {
            GLOBAL_ENABLED = true;
        }
        if (!ObjectUtils.isNotEmpty(DOMAIN)) {
            DOMAIN = "";
        }
    }

}
