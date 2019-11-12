/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common.entity;

import cn.ikangxu.boot.apidoc.common.RequestHandler;
import com.google.common.base.Predicate;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author kangxu [xukang@engine3d.com]
 * @version v1.0
 * @className Docket
 * @description
 * @date 2019/11/8 12:55
 */
@Data
@Builder
public class Docket {
    private String groupName = "default";
    private DocInfo docInfo;
    // 是否启用文档
    private boolean enabled;
    // 是否可以Debug
    private boolean isDebuger;
    List<RequestHandler> apis;
}
