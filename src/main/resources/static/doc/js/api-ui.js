$(function () {
    var _length = $('.tx-this').length;
    if (_length > 0) {
        $('.tx-fluid .content-body >div').addClass('display-none');
    }

    $('.sdk-body .left .content  ul > li').live('click', function (event) {
        //if (event.target.parentElement == this || event.target.parentElement.parentElement == this) {
        $('.sdk-body .left .content  ul > li').each(function (index, element) {
            $(this).removeClass('tx-itemed');
        })
        $(this).addClass('tx-itemed');
        //}
    })

    $('ul.nav-tabs > li').live('click', function (event) {
        var href = $(this).find("a").attr("href");

        $('ul.nav-tabs > li').removeClass("active");
        $('ul.nav-tabs > li').find(".el-icon-close").hide();
        $(this).addClass("active");
        $(this).find(".el-icon-close").show();

        $(".tab-content .tab-pane").hide();
        $(".tab-content .tab-pane" + href).show();


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
        $(".tx-fluid").mCustomScrollbar("scrollTo", "top");

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
    $(".tx-fluid,.sdk-body>.left>.content>div.menu, .tab-content").mCustomScrollbar({
        theme: "minimal-dark",
        scrollTo: "bottom"
    });

    util.get('/v1/api-doc', function (data) {
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
                callback_page_html(_result.tabs);

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
                        callback_page_html(_tabs[_i].paths[_j]);
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
        $('.tx-fluid .content-body >div').addClass('display-none');
        $('.tx-fluid .content-body >div:last-child').removeClass('display-none');
    }
}

var callback_page_html = function (data) {
    var _html = "";
    for (var _i in data) {
        if (data[_i].paths.length > 0) {
            for (var _j in data[_i].paths) {
                // if (data[_i].paths[_j].id === $(this).attr('id')) {
                // callback_page_html(data[_i].paths[_j]);
                _html += "<div id=\"tab_content_" + data[_i].paths[_j].id + "\" class=\"tab-pane\">\n" +
                    "\t<div class=\"tx-fluid\">\n" +
                    "\t\t<div class=\"content-body\">\n" +
                    "\t\t\t<div>\n" +
                    "\t\t\t\t<span>接口说明</span>\n" +
                    "\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t<div class=\"alert-warning description\">\n" +
                    "\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div>\n" +
                    "\t\t\t\t<span>API地址</span>\n" +
                    "\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t<div class=\"url\">\n" + data[_i].paths[_j].url +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div>\n" +
                    "\t\t\t\t<span>是否需要登录</span>\n" +
                    "\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t<div class=\"islogin\"></div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div>\n" +
                    "\t\t\t\t<span>请求字段说明</span>\n" +
                    "\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t<div class=\"params\">\n" +
                    "\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div>\n" +
                    "\t\t\t\t<span>响应字段说明</span>\n" +
                    "\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t<div class=\"response\">\n" +
                    "\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div>\n" +
                    "\t\t\t\t<span>响应成功示例</span>\n" +
                    "\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t<div class=\"response-success\">\n" +
                    "\t\t\t\t\t<pre id=\"success-result\">\n" +
                    "\n" +
                    "\t\t\t\t\t</pre>\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div>\n" +
                    "\t\t\t\t<span>响应失败示例</span>\n" +
                    "\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t<div class=\"response-fail\">\n" +
                    "\t\t\t\t\t<pre id=\"fail-result\">\n" +
                    "\n" +
                    "\t\t\t\t\t</pre>\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div>\n" +
                    "\t\t\t\t<span>响应接受类型</span>\n" +
                    "\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t<div class=\"produces\"></div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div>\n" +
                    "\t\t\t\t<span>响应状态码</span>\n" +
                    "\t\t\t\t<div class=\"headline\"></div>\n" +
                    "\t\t\t\t<div class=\"responses\">\n" +
                    "\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t</div>\n" +
                    "\t</div>\n" +
                    "</div>";
                //}
            }
        }
    }

    $("#content-div>div>div").append(_html);

    // $('.tx-fluid .content-body >div').removeClass('display-none');
    // $('.tx-fluid .content-body >div:last-child').addClass('display-none');
    // $('.content-body .description').empty().append($.trim(data.description) === '' ? '无' : data.description);
    // $('.content-body .url').empty().append('<cite>' + data.method + '</cite>&nbsp;' + data.url);
    // $('.content-body .islogin').empty().append(data.islogin ? '是' : '否');
    // var _parameters = data.parameters, _parameters_html = '';
    // if (_parameters.length > 0) {
    //     _parameters_html += '<table>'
    //         + '	<thead>'
    //         + '	<tr>'
    //         + '		<th>参数</th>'
    //         + '		<th>类型</th>'
    //         + '		<th>请求类型</th>'
    //         + '		<th>是否必须</th>'
    //         + '		<th>说明</th>'
    //         + '	</tr>'
    //         + '	</thead>'
    //         + '	<tbody>';
    //     for (var _i in _parameters) {
    //         _parameters_html += '	<tr>'
    //             + '		<td>' + _parameters[_i].name + '</td>'
    //             + '		<td>' + _parameters[_i].dataType + '</td>'
    //             + '		<td>' + _parameters[_i].paramType + '</td>'
    //             + '		<td>' + (_parameters[_i].required ? '是' : '否') + '</td>'
    //             + '		<td>' + _parameters[_i].description + '</td>'
    //             + '	</tr>';
    //     }
    //     _parameters_html += '	</tbody>'
    //         + '</table>';
    // } else {
    //     _parameters_html = '无';
    // }
    // $('.content-body .params').empty().append(_parameters_html);
    // var _response = data.responseExample, _response_html = '';
    // if (_response && _response.info.length > 0) {
    //     var _response_info = _response.info;
    //     _response_html += '<table>'
    //         + '	<thead>'
    //         + '	<tr>'
    //         + '		<th>参数</th>'
    //         + '		<th>类型</th>'
    //         + '		<th>说明</th>'
    //         + '	</tr>'
    //         + '	</thead>'
    //         + '	<tbody>';
    //     for (var _i in _response.info) {
    //         _response_html += '	<tr>'
    //             + '		<td>' + _response.info[_i].field + '</td>'
    //             + '		<td>' + _response.info[_i].type + '</td>'
    //             + '		<td>' + _response.info[_i].description + '</td>'
    //             + '	</tr>';
    //     }
    //     _response_html += '	</tbody>'
    //         + '</table>'
    // } else {
    //     _response_html = '无';
    // }
    // $('.content-body .response').empty().append(_response_html);
    // if(_response) {
    //     var _success = _response.success;
    //     document.getElementById('success-result').innerHTML = util.syntaxhighlight_json(_success);
    //     var _fail = _response.fail;
    //     document.getElementById('fail-result').innerHTML = util.syntaxhighlight_json(_fail);
    // }
    // var _produces = data.produces, _produces_html = '';
    // for (var _i in _produces) {
    //     _produces_html += _produces[_i] + '<br />';
    // }
    // $('.content-body .produces').empty().append(_produces_html);
    // var _responses = data.responses, _responses_html = '';
    // if (_responses.length > 0) {
    //     _responses_html += '<table>'
    //         + '	<thead>'
    //         + '	<tr>'
    //         + '		<th>HTTP状态码</th>'
    //         + '		<th>原因</th>'
    //         + '	</tr>'
    //         + '	</thead>'
    //         + '	<tbody>';
    //     for (var _i in _responses) {
    //         _responses_html += '	<tr>'
    //             + '		<td>' + _responses[_i].code + '</td>'
    //             + '		<td>' + _responses[_i].msg + '</td>'
    //             + '	</tr>';
    //     }
    //     _responses_html += '	</tbody>'
    //         + '</table>';
    // } else {
    //     _responses_html = '无';
    // }
    // $('.content-body .responses').empty().append(_responses_html);
    //
    // dp.SyntaxHighlighter.ClipboardSwf = './SyntaxHighlighter/clipboard.swf';
    // dp.SyntaxHighlighter.HighlightAll("code");
}