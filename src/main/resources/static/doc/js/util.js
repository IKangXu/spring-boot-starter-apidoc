/**
 * Created by kangxu on 2018/7/11.
 */
(function (win, $) {
    /*ajax post方法*/
    var post_fun = function (url, header, data, handler, failHandler, alwaysHandler, dataType) {
        var dtd = $.Deferred(),
            data;
        $.ajax({
            url: url,
            data: data || {},
            dataType: dataType || 'json',
            type: 'POST',
            async: false,
            headers: header || {},
            xhrFields: {
                withCredentials: true
            }
        }).done(function (data) {
            data = handler ? handler(data) : data;
            dtd.resolve(data);
        }).fail(function (data) {
            data = failHandler ? failHandler(data) : data;
            dtd.reject(data);
        }).always(function () {
            alwaysHandler(data);
        });
        return dtd.promise();
    };

    /*ajax get方法*/
    var get_fun = function (url, handler, failHandler, dataType, async) {
        // 生成Deferred对象
        var dat = $.Deferred(),
            data;
        $.ajax({
            url: url,
            data: data || {},
            dataType: dataType || 'json',
            type: "get",
            async: async || false,
            xhrFields: {
                withCredentials: true
            },
            beforeSend: function () {
            }
        }).done(function (data) {
            data = handler ? handler(data) : data;
            dat.resolve(data);
        }).fail(function (data) {
            data = failHandler ? failHandler(data) : data;
            dat.reject(data);
        });
        return dat.promise();
    };

    //js获取项目根路径，如： http://localhost:8083/uimcardprj
    var get_root_path = function () {
        //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
        var curWwwPath = window.document.location.href;

        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);

        //获取主机地址，如： http://localhost:8083
        var localhostPath = curWwwPath.substring(0, pos) + '/';

        //获取带"/"的项目名，如：/uimcardprj
        // var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

        return (localhostPath);
    }
    /*获取URL参数*/
    var get_query_param = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    /*写cookies*/
    var set_cookie = function (name, value) {
        var Days = 30;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
    }
    /*读取cookies */
    var get_cookie = function (name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

        if (arr = document.cookie.match(reg))

            return unescape(arr[2]);
        else
            return null;
    }
    /*删除cookies*/
    var del_cookie = function (name) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        if (cval != null)
            document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
    }

    var syntaxhighlight_json = function (json) {
        if (typeof json != 'string') {
            json = JSON.stringify(json, undefined, 2);
        }
        json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');
        return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
            var cls = 'number';
            if (/^"/.test(match)) {
                if (/:$/.test(match)) {
                    cls = 'key';
                } else {
                    cls = 'string';
                }
            } else if (/true|false/.test(match)) {
                cls = 'boolean';
            } else if (/null/.test(match)) {
                cls = 'null';
            }
            return '<span class="' + cls + '">' + match + '</span>';
        });
    }

    /**
     * 判断是否存在指定的标签页
     * @param tabMainName
     * @param tabName
     * @returns {Boolean}
     */
    var checkTabIsExists = function(tabMainName, tabName){
        var tab = $("#"+tabMainName+" > #tab_li_"+tabName);
        //console.log(tab.length)
        return tab.length > 0;
    }

    /**
     * 关闭标签页
     * @param button
     */
    var closeTab = function(button) {

        //通过该button找到对应li标签的id
        var li_id = $(button).parent().parent().attr('id');
        var id = li_id.replace("tab_li_","");

        //如果关闭的是当前激活的TAB，激活他的前一个TAB
        $("li.active").each(function () {
            if($(this).attr('id') == li_id)
            {
                $(this).prev().find("a").click();
            }
        })
        // if ($("li.active").attr('id') == li_id) {
        //     $("li.active").prev().find("a").click();
        // }

        //关闭TAB
        $("#" + li_id).remove();
        $("#tab_content_" + id).remove();
    };

    var refreshTab = function(button) {
        //通过该button找到对应li标签的id
        var li_id = $(button).parent().parent().attr('id');
        var id = li_id.replace("tab_li_","");

        $("#tab_content_" + id + " > iframe").attr('src', $("#tab_content_" + id + " > iframe").attr('src'));
    }

    /**
     * 增加标签页
     */
    var addTab = function(options) {
        //option:
        //tabMainName:tab标签页所在的容器
        //tabName:当前tab的名称
        //tabTitle:当前tab的标题
        //tabUrl:当前tab所指向的URL地址
        var exists = checkTabIsExists(options.tabMainName, options.tabName);
        if(exists){
            $("#tab_a_"+options.tabName).click();
        } else {
            $("#"+options.tabMainName).append('<li id="tab_li_'+options.tabName+'"><a href="#tab_content_'+options.tabName+'" data-toggle="tab" id="tab_a_'+options.tabName+'">'+options.tabTitle+'</a>&nbsp;<span class="el-icon-close"></span></li>');

            //固定TAB中IFRAME高度
            mainHeight = $(document.body).height() - 165;
            $("#tab_a_"+options.tabName).click();
        }
    }

    var tabAdd = function(tabMainName,tabName,tabTitle,tabUrl) {
        //option:
        //tabMainName:tab标签页所在的容器
        //tabName:当前tab的名称
        //tabTitle:当前tab的标题
        //tabUrl:当前tab所指向的URL地址
        var options = {};
        options.tabMainName = tabMainName;
        options.tabName = tabName;
        options.tabTitle = tabTitle;
        options.tabUrl = tabUrl;
        addTab(options);
    }

    win.util = {
        post: post_fun,
        get: get_fun,
        get_root_path: get_root_path,
        get_query_param: get_query_param,
        cookie: {
            set_cookie: set_cookie,
            get_cookie: get_cookie,
            del_cookie: del_cookie
        },
        syntaxhighlight_json: syntaxhighlight_json,
        tab: {
            tabAdd: tabAdd
        }
    };
})(window, jQuery);