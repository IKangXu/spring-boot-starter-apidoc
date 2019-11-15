$(function () {
    var _length = $('.tx-this').length;
    if (_length > 0) {
        // $('.tx-fluid .content-body >div').addClass('display-none');
    }

    $('.sdk-body > .left > .content  ul > li').live('click', function (event) {
        if (event.target.parentElement == this) {
            if ($(this).hasClass('tx-itemed')) {
                $(this).removeClass('tx-itemed');
            } else {
                $(this).addClass('tx-itemed');
            }
        }
    })

    $('ul.nav-tabs > li').live('click', function (event) {
        var href = $(this).find("a").attr("href");

        $('ul.nav-tabs > li').removeClass("active");
        $('ul.nav-tabs > li').find(".el-icon-close").hide();
        $(this).addClass("active");
        $(this).find(".el-icon-close").show();

        $(".tab-content .tab-pane").removeClass("active");
        $(".tab-content .tab-pane").hide();
        $(".tab-content .tab-pane" + href).show();

        if (!$("#" + href.replace("#tab_content_", "")).hasClass('tx-this')) {
            $("#" + href.replace("#tab_content_", "")).click();
            $("#" + href.replace("#tab_content_", "")).parent().parent().addClass("tx-itemed");
        }

        // 将文档和调试调换
        if ($('.pane-menu .doc_' + href.replace("#tab_content_", "")).hasClass('active')) {
            $('.pane-content .doc_' + href.replace("#tab_content_", "")).addClass('active');
            $('.pane-content .doc_' + href.replace("#tab_content_", "")).siblings().removeClass('active');
        }
        if ($('.pane-menu .debuger_' + href.replace("#tab_content_", "")).hasClass('active')) {
            $('.pane-content .debuger_' + href.replace("#tab_content_", "")).addClass('active');
            $('.pane-content .debuger_' + href.replace("#tab_content_", "")).siblings().removeClass('active');
        }
    })

    $('ul.nav-tabs > li').live('mouseover', function (event) {
        if ($(this).hasClass('active')) {
            return;
        }
        $(this).find(".el-icon-close").show();
    })
    $('ul.nav-tabs > li').live('mouseout', function (event) {
        if ($(this).hasClass('active')) {
            return;
        }
        $(this).find(".el-icon-close").hide();
    })

    // tab关闭事件
    $('ul.nav-tabs > li > .el-icon-close').live('click', function (event) {

        var href;
        // 选中上级兄弟节点
        if ($(this).parent().next().length == 0) {
            $(this).parent().prev().addClass('active');
            $(this).parent().prev().find(".el-icon-close").show();

            href = $(this).parent().prev().find("a").attr("href")
        } else {
            $(this).parent().next().addClass('active');
            $(this).parent().next().find(".el-icon-close").show();
            href = $(this).parent().next().find("a").attr("href")
        }

        $(".tab-content .tab-pane").removeClass("active");
        $(".tab-content .tab-pane").hide();
        $(".tab-content .tab-pane" + href).show();

        $(this).parent().remove();

        // 禁止触发父级节点事件
        event = event || window.event;
        if (event.stopPropagation) { //W3C阻止冒泡方法
            event.stopPropagation();
        } else {
            event.cancelBubble = true; //IE阻止冒泡方法
        }
    })

    $('.tab-pane>.pane-menu > div').live('click', function (event) {
        var tag = $(this).attr("data-tag");
        $('.pane-content div.content').each(function (index, element) {
            if ($(this).attr("data-tag") === tag) {
                $(this).addClass('active');
            } else {
                $(this).removeClass('active');
            }
        })
        $(this).siblings().removeClass('active');
        $(this).addClass('active');
    })

    // 监听输入框事件
    $('.search>input[type="text"]').on('input propertychange', function () {
        var _keywork = $('#search').val();
        // $('.menu dd>a').each(function (index, element) {
        //     var _val = $(this).text();
        //     if (_keywork === _val) {
        //         alert();
        //     }
        // })
        if ($.trim(_keywork) === '') {
            var _html = show_menu_fun(window._tabs);
            // console.info(_html);
            $('.left .content>div.menu ul').empty().append(_html);
            init();
            return;
        }
        var _all_tab = window._tabs;
        var _search_result = new Array();
        for (var _i in _all_tab) {
            if (_all_tab[_i].paths.length > 0) {
                var _cnt = -1;
                var _new_arr = new Array();
                for (var _j in _all_tab[_i].paths) {
                    if (_all_tab[_i].paths[_j].name.indexOf(_keywork) >= 0) {
                        _new_arr.push(_all_tab[_i].paths[_j]);
                        _cnt++;
                    }
                }
                if (!(_cnt === -1)) {
                    var attrs = {};
                    for (var p in _all_tab[_i]) {
                        attrs[p] = _all_tab[_i][p];
                    }
                    attrs.paths = _new_arr;
                    _search_result.push(attrs);
                }
            }
        }
        console.log(_search_result)
        var _html = show_menu_fun(_search_result);
        // console.info(_html);
        $('.left .content>div.menu ul').empty().append(_html);
        init();

    })
    $('p.intro:eq(0)').animate({height: 'toggle', opacity: 'toggle'}, "slow");
    $('.intro-h').live('click', function () {
        $('p.intro').each(function (index, element) {
            // $(this).removeClass('intro-this');
            $(this).animate({height: 'hide', opacity: 'hide'}, "slow");
        })
        var _idx = $('.intro-h').index(this);
        // $('p.intro:eq('+_idx+')').addClass('intro-this');
        $('p.intro:eq(' + _idx + ')').animate({height: 'toggle', opacity: 'toggle'}, "slow");
    })
    $('.sdk-body .left .content dd').live('click', function (event) {
        //if(event.target.parentElement == this){
        $('.sdk-body .left .content dd').each(function (index, element) {
            $(this).removeClass('tx-this');
        })
        $(this).addClass('tx-this');
        // for (var _i in window._tabs) {
        //     if (window._tabs[_i].paths.length > 0) {
        //         for (var _j in window._tabs[_i].paths) {
        //             if (window._tabs[_i].paths[_j].id === $(this).attr('id')) {
        //                 callback_page_html(window._tabs[_i].paths[_j]);
        //                 break;
        //             }
        //         }
        //     }
        // }
        $('#samplecode').attr('class', 'javascript');
        $('sign').each(function (index, element) {
            $(this).removeClass('sign-selected');
        })
        $('sign[tag="javascript"]').addClass('sign-selected');
        // $(".tx-fluid").mCustomScrollbar("scrollTo", "top");

        var tabTitle = $(this).find("a").attr("title");
        var tabName = $(this).attr("id");
        util.tab.tabAdd("tab-div", tabName, tabTitle, "content-div")
        //}
    })

    $('sign').live('click', function () {
        var _tag = $(this).attr('tag');
        var _id = location.href.split('#')[1];
        $('sign').each(function (index, element) {
            $(this).removeClass('sign-selected');
        })
        $(this).addClass('sign-selected');
        for (var _i in window._tabs) {
            if (window._tabs[_i].paths.length > 0) {
                for (var _j in window._tabs[_i].paths) {
                    if (window._tabs[_i].paths[_j].id === _id) {
                        var samplecode = window._tabs[_i].paths[_j].samplecode;
                        $('.dp-highlighter').remove();
                        $('.content-body #samplecode').empty().append(samplecode[_tag]);

                        $('#samplecode').attr('class', _tag);

                        dp.SyntaxHighlighter.ClipboardSwf = './SyntaxHighlighter/clipboard.swf';
                        dp.SyntaxHighlighter.HighlightAll("code");
                        break;
                    }
                }
            }
        }

    })

    // 初始化滚动条
    $(".tx-fluid,.sdk-body>.left>.content>div.menu").mCustomScrollbar({
        theme: "minimal-dark",
        scrollTo: "bottom"
    });

    util.get('v1/api-doc', function (data) {
        if (data.code === 200) {
            if (!($.trim(data.data) === '')) {
                var _result = data.data;
                // console.info(_result);
                // 改变标题
                $('.right>.top>span.small-title').text(_result.docInfo.title);
                $('.right>.top>span.version>strong').text($.trim(_result.docInfo.version) === '' ? 'v1.0.0' : _result.docInfo.version);

                window._tabs = _result.tabs;

                var _html = show_menu_fun(_result.tabs);
                $('.log-update').empty().append(_result.log);
                $('p.intro:eq(0)').animate({height: 'toggle', opacity: 'toggle'}, "slow");

                // console.info(_html);
                $('.left .content>div.menu ul').empty().append(_html);

                // 初始化右侧内容结构
                callback_page_html(_result);

                init();
            }
        } else {

        }
    }, function (data) {

    }, 'json');

})

var show_menu_fun = function (data) {
    data = sort(data);
    var _html = '';
    for (var _i in data) {
        var _class = '';
        if (_i === '0') {
            _class = 'tx-itemed';
        }
        _html += '<li class="' + _class + '">'
            + '	<a href="javascript:;">'
            + '		<cite>      ' + data[_i].name + '</cite>'
            + '		<span class="tx-nav-more"></span>'
            + '	</a>'
            + '	<dl>';
        if (data[_i].paths.length > 0) {
            for (var _j in data[_i].paths) {
                if (data[_i].paths[_j].discarded) {
                    _html += '		<dd id="' + data[_i].paths[_j].id + '"><a title="' + data[_i].paths[_j].name + '" class="line-through" href="#' + data[_i].paths[_j].id + '">' + data[_i].paths[_j].name + '</a></dd>'
                } else {
                    _html += '		<dd id="' + data[_i].paths[_j].id + '"><a title="' + data[_i].paths[_j].name + '" href="#' + data[_i].paths[_j].id + '">' + data[_i].paths[_j].name + '</a></dd>'
                }
            }
        }
        _html += '	</dl>'
            + '</li>';
    }

    function sort(elements) {
        var len = elements.length;
        for (var i = 0; i < len; i++) {
            for (var j = 0; j < len - 1 - i; j++) {
                if (elements[j].sort > elements[j + 1].sort) {        // 相邻元素两两对比
                    var temp = elements[j + 1];        // 元素交换
                    elements[j + 1] = elements[j];
                    elements[j] = temp;
                }
            }
        }
        return elements;
    }

    return _html;
}

var init = function () {
    if (!(location.href.indexOf("#") === -1)) {
        var _id = location.href.split('#')[1], _obj = {};
        for (var _i in _tabs) {
            if (_tabs[_i].paths.length > 0) {
                for (var _j in _tabs[_i].paths) {
                    if (_tabs[_i].paths[_j].id === _id) {
                        // callback_page_html(_tabs[_i].paths[_j]);
                        $('.sdk-body .left .content>div.menu ul > li').each(function (index, element) {
                            $(this).removeClass('tx-itemed');
                        })
                        $('.sdk-body .left .content dd').each(function (index, element) {
                            $(this).removeClass('tx-this');
                        })
                        $('#' + _id).addClass('tx-this');
                        $($('#' + _id)[0].parentElement.parentElement).addClass('tx-itemed');
                        break;
                    }
                }
            }
        }
    } else {
        // $('.tx-fluid .content-body >div').addClass('display-none');
        // $('.tx-fluid .content-body >div:last-child').removeClass('display-none');
    }
}

var callback_page_html = function (data) {

    var tags = data.tabs;
    var debuger = data.debuger;

    var _html = "";
    for (var _i in tags) {
        if (tags[_i].paths.length > 0) {
            for (var _j in tags[_i].paths) {
                _html += "<div id=\"tab_content_" + tags[_i].paths[_j].id + "\" class=\"tab-pane\">\n" +
                    "\t<div class=\"pane-menu\">\n" +
                    "\t\t<div data-tag=\"doc_" + tags[_i].paths[_j].id + "\" class=\"active doc_" + tags[_i].paths[_j].id + "\"><span class=\"pane-icon-doc\"></span>&nbsp;文档</div>\n";
                if (debuger) {
                    _html += "\t\t<div data-tag=\"debuger_" + tags[_i].paths[_j].id + "\" class=\"debuger_" + tags[_i].paths[_j].id + "\"><span class=\"pane-icon-debuger\"></span>&nbsp;调试</div>\n";
                }
                _html += "\t</div>\n" +
                    "\t<div class=\"pane-content\">\n" +
                    "\t\t<div class=\"content doc active doc_" + tags[_i].paths[_j].id + "\" data-tag=\"doc_" + tags[_i].paths[_j].id + "\">\n" +
                    "\t\t\t<div class=\"tx-fluid\">\n" +
                    "\t\t\t\t<div class=\"content-body\">\n" +
                    "\t\t\t\t\t<div>\n" +
                    "\t\t\t\t\t\t<span>接口说明</span>\n" +
                    "\t\t\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t\t\t<div class=\"alert-warning description\">\n" +
                    "\n" + tags[_i].paths[_j].description +
                    "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t<div>\n" +
                    "\t\t\t\t\t\t<span>API地址</span>\n" +
                    "\t\t\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t\t\t<div class=\"url\"><pre>\n";
                var _obj = {
                    method: tags[_i].paths[_j].method,
                    url: tags[_i].paths[_j].url
                }
                _html += (tags[_i].paths[_j].method + "\t" + tags[_i].paths[_j].url + "");
                _html += "</pre>\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t<div>\n" +
                    "\t\t\t\t\t\t<span>请求字段说明</span>\n" +
                    "\t\t\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t\t\t<div class=\"params\">\n";

                var parameters = tags[_i].paths[_j].parameters;
                _html += '<table>'
                    + '	<thead>'
                    + '	<tr>'
                    + '		<th>参数</th>'
                    + '		<th>类型</th>'
                    + '		<th>请求类型</th>'
                    + '		<th>是否必须</th>'
                    + '		<th>说明</th>'
                    + '		<th>示例</th>'
                    + '	</tr>'
                    + '	</thead>'
                    + '	<tbody>';
                for (var _k = 0; _k < parameters.length; _k++) {
                    var _parameter = parameters[_k];

                    _html += '	<tr>'
                        + '		<td>' + _parameter.name + '</td>'
                        + '		<td>' + _parameter.dataType + '</td>'
                        + '		<td>' + _parameter.paramType + '</td>'
                        + '		<td>' + (_parameter.required ? '是' : '否') + '</td>'
                        + '		<td>' + _parameter.description + '</td>'
                        + '		<td>' + _parameter.example + '</td>'
                        + '	</tr>';
                }
                _html += '	</tbody></table>';

                _html += "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t<div>\n" +
                    "\t\t\t\t\t\t<span>响应字段说明</span>\n" +
                    "\t\t\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t\t\t<div class=\"response\">\n";

                var _responseExample = tags[_i].paths[_j].responseExample;
                if (_responseExample) {
                    var _response_info = _responseExample.info;
                    _html += '<table>'
                        + '	<thead>'
                        + '	<tr>'
                        + '		<th>参数</th>'
                        + '		<th>类型</th>'
                        + '		<th>说明</th>'
                        + '		<th>示例</th>'
                        + '	</tr>'
                        + '	</thead>'
                        + '	<tbody>';
                    for (var _k = 0; _k < _response_info.length; _k++) {
                        _html += '	<tr>'
                            + '		<td>' + _response_info[_k].field + '</td>'
                            + '		<td>' + _response_info[_k].type + '</td>'
                            + '		<td>' + _response_info[_k].description + '</td>'
                            + '		<td>' + _response_info[_k].example + '</td>'
                            + '	</tr>';
                    }
                    _html += '	</tbody>'
                        + '</table>';
                }

                _html += "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t<div>\n" +
                    "\t\t\t\t\t\t<span>响应成功示例</span>\n" +
                    "\t\t\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t\t\t<div class=\"response-success\">\n" +
                    "\t\t\t\t\t\t\t<pre id=\"success-result\">\n";
                if (_responseExample) {
                    _html += util.syntaxhighlight_json(_responseExample.success);
                }
                _html += "</pre>\n" +
                    "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t<div>\n" +
                    "\t\t\t\t\t\t<span>响应失败示例</span>\n" +
                    "\t\t\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t\t\t<div class=\"response-fail\">\n" +
                    "\t\t\t\t\t\t\t<pre id=\"fail-result\">\n";
                if (_responseExample) {
                    _html += util.syntaxhighlight_json(_responseExample.fail);
                }
                _html += "</pre>\n" +
                    "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t<div>\n" +
                    "\t\t\t\t\t\t<span>响应接受类型</span>\n" +
                    "\t\t\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t\t\t<div class=\"produces\">\n" +
                    "\t\t\t\t\t\t\t<pre id=\"fail-result\">\n";

                var _produces = tags[_i].paths[_j].produces;
                if (_produces.length > 0) {
                    var _produce_html = "[";
                    for (var _k in _produces) {
                        _produce_html += ("\"" + _produces[_k].type + "/" + _produces[_k].subtype);
                        if (_produces[_k].charset) {
                            _produce_html += (";charset=" + _produces[_k].charset);
                        }
                        _produce_html += "\",";
                    }
                    _produce_html = _produce_html.substring(0, _produce_html.length - 1);
                    _produce_html += "]";

                    _html += util.syntaxhighlight_json(_produce_html);
                } else {
                    _html += "无";
                }

                _html += "</pre>\n" +
                    "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t<div>\n" +
                    "\t\t\t\t\t\t<span>响应状态码</span>\n" +
                    "\t\t\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t\t\t<div class=\"responses\">\n";
                var _responses = tags[_i].paths[_j].responses;
                if (_responses.length > 0) {
                    _html += '<table>'
                        + '	<thead>'
                        + '	<tr>'
                        + '		<th>HTTP状态码</th>'
                        + '		<th>原因</th>'
                        + '		<th>描述</th>'
                        + '	</tr>'
                        + '	</thead>'
                        + '	<tbody>';
                    for (var _k in _responses) {
                        _html += '	<tr>'
                            + '		<td>' + _responses[_k].code + '</td>'
                            + '		<td>' + _responses[_k].msg + '</td>'
                            + '		<td>' + _responses[_k].description + '</td>'
                            + '	</tr>';
                    }
                    _html += '	</tbody>'
                        + '</table>';
                }
                _html += "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t</div>\n";
                if (debuger) {
                    _html += "\t\t<div class=\"content debuger debuger_" + tags[_i].paths[_j].id + "\" data-tag=\"debuger_" + tags[_i].paths[_j].id + "\">\n" +
                        "\t\t\t我是调试页面\n" +
                        "\t\t</div>\n";
                }
                _html += "\t</div>\n" +
                    "</div>";
            }
        }
    }

    $("#content-div").append(_html);
    $(".tab-pane .pane-content").mCustomScrollbar({
        scrollButtons: {enable: true},
        theme: "minimal-dark",
        scrollbarPosition: "outside",
        mouseWheelPixels: 200
    });
}