/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc.common;

import cn.ikangxu.boot.apidoc.common.entity.DocInfo;
import cn.ikangxu.boot.apidoc.common.entity.Tab;
import com.google.common.collect.Multimap;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @className Documentation
 * @description 
 * @author kangxu [xukang@engine3d.com]
 * @date 2019/11/11 11:19
 * @version v1.0
 */
@Data
public class Documentation {
    private DocInfo docInfo;

    private String groupName;
    // 是否启用文档
    private boolean enabled;
    // 是否可以Debug
    private boolean isDebuger;


    private Collection<Tab> tabs = new ArrayList<>();

}
