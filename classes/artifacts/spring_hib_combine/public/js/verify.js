/**
 * Created with JetBrains PhpStorm.
 * User: Administrator
 * Date: 13-9-12
 * Time: 下午3:36
 * To change this template use File | Settings | File Templates.
 */
//资源服务器下载地址
var resUrl = "http://wkf.oboard.net/ResServ/files/";
var serverUrl="http://hd.weipass.cn:80";
//var serverUrl = "http://192.168.1.186:7070";
var webRoot = "/padmin/";


//微信QQ表情
var qqFace = Array("/::)", "/::~", "/::B", "/::|", "/:8-)", "/::<", "/::$", "/::X", "/::Z", "/::’(", "/::-|", "/::@", "/::P", "/::D", "/::O", "/::(", "/::+", "/:–b", "/::Q", "/::T", "/:,@P", "/:,@-D", "/::d", "/:,@o", "/::g", "/:|-)", "/::!", "/::L", "/::>", "/::,@", "/:,@f", "/::-S", "/:?", "/:,@x", "/:,@@", "/::8", "/:,@!", "/:!!!", "/:xx", "/:bye", "/:wipe", "/:dig", "/:handclap", "/:&-(", "/:&-(", "/:<@", "/:@>", "/::-O", "/:>-|", "/:P-(", "/::’|", "/:X-)", "/::*", "/:@x", "/:8*", "/:pd", "/:<W>", "/:beer", "/:basketb", "/:oo", "/:coffee", "/:eat", "/:pig", "/:rose", "/:fade", "/:showlove", "/:heart", "/:break", "/:cake", "/:li");
String.prototype.replaceAll = function (s1, s2) {
    return this.replace(new RegExp(s1, "gm"), s2);
}

$(function () {
    setLeftHeight();
    intervalTime = window.setInterval(function () {
        timeAfterTime();
    }, 1000);
});


//验证
function verifyForm(objForm) {
    var from = $("#" + objForm);
    var ck = true;
    if (from.size() > 0) {
        var input = from.find("input:text,input:password,textarea,input:hidden");
        if (input.size() > 0) {
            var ma = new Array();
            input.each(function (index) {
                var inputOne = $(this);
                var v = inputOne.attr("v");
                var m = inputOne.attr("m");
                if (v && m) {
                    var vs = v.split(",");
                    var vt;
                    var valueStr = $.trim(inputOne.val());
                    for (vt in vs) {
                        var tvt = vs[vt];
                        switch (tvt) {
                            case "required":
                                if (valueStr == '' || valueStr == ' ') {
                                    inputOne.addClass("req");
                                    inputOne.focus();
                                    ck = false;
                                    ma.push(m + "必须填写");
                                } else {
                                    inputOne.removeClass("req");
                                }
                                break;
                            case "number":
                                if (isNaN(valueStr)) {
                                    inputOne.addClass("req");
                                    inputOne.focus();
                                    ma.push(m + "必须为数字");
                                    ck = false;
                                } else {
                                    inputOne.removeClass("req");
                                }
                                break;
                            case "int":
                                if (valueStr < 0 || valueStr != parseInt(valueStr)) {
                                    inputOne.addClass("req");
                                    inputOne.focus();
                                    ma.push(m + "必须为正整数");
                                    ck = false;
                                } else {
                                    if (valueStr.indexOf(".") == -1) {
                                        inputOne.removeClass("req");
                                    } else {
                                        inputOne.addClass("req");
                                        inputOne.focus();
                                        ma.push(m + "不能有小数点,必须为正整数");
                                        ck = false;
                                    }
                                }
                                break;
                            case "email":
                                var reg = /^([a-z0-9A-Z]+[_|\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/;
                                var test = reg.test(valueStr);
                                if (test) {
                                    inputOne.removeClass("req");
                                } else {
                                    inputOne.addClass("req");
                                    inputOne.focus();
                                    ma.push(m + "格式不正确");
                                    ck = false;
                                }
                                break;
                            case "mobile":
                                var reg = /(^[1][0-9]{10}$)/;
                                var test = reg.test(valueStr);
                                if (test) {
                                    inputOne.removeClass("req");
                                } else {
                                    inputOne.addClass("req");
                                    inputOne.focus();
                                    ma.push(m + "格式不正确");
                                    ck = false;
                                }
                                break;

                        }
                        if (!ck) {
                            break;
                        }
                    }
                    if (!ck) {
                        dangerHint(ma.join());
                        return false;
                    }
                }
            });
        } else {
            alert('在表单内未找到要检测的元素，请检查');
            ck = false;
        }
        return ck;
    } else {
        alert('要检测的表单为空，请检查');
        return false;
    }
}

/*
 * 改变按钮的一些状态和文字什么的status=on off
 * */
function buttonStatus(obj, text, status) {
    var button = $("#" + obj);
    if (button.val() == '') {
        button.text(text);
    } else {
        button.val(text);
    }
    if (status == 'off') {
        button.attr("disabled", "disabled");
    } else {
        button.removeAttr("disabled");
        button.removeClass("disabled");
    }
}

//全选某个复选框
function CheckAll(val, name) {
    $("input[name='" + name + "']").each(function () {
        this.checked = val;
    });
}
/*
 * 返回已选数据的值，以,号分隔
 * */
function returnCheckBox(name, getvaluetype) {
    var checkBox = $(":checkbox[name='" + name + "']:checked");
    if (checkBox.length > 0) {
        var isCheckVal = new Array();
        checkBox.each(function (i) {
            isCheckVal[i] = getvaluetype ? $(this).attr(getvaluetype) : $(this).attr("value");
        });
        return isCheckVal.join();
    } else {
        return false;
    }
}

function getLocalTimes(nS) {
    var dd = nS;
    if (nS == '' || nS == null) {
        dd = Date.parse(new Date());
    }
    var d = new Date(parseInt(dd));
    var year = d.getYear();
    var month = d.getMonth() + 1;
    var date = d.getDate();
    var hour = d.getHours();
    var minute = d.getMinutes();
    var second = d.getSeconds();
    return month + "月" + date + "日 " + hour + ":" + minute + ":" + second;
}

//获得微信用户的性别
function getWxSex(sex) {
    if (sex == "1") {
        return "男";
    }
    else if (sex == "2") {
        return "女";
    }
    else {
        return "未知";
    }
}

//重定向至本页面
function refThisPage() {
    window.location.href = window.location.href.replace(/#/g, '');
}
function delImg() {
    $("#tempimg").html('');
    $("#IMG").val('');
    $("#SMALLIMG").val('');
}
/*
 * 定时任务
 * */
function timeAfterTime() {
    setLeftHeight();
}
/*
 * 设置左侧栏的高度
 * */
function setLeftHeight() {
    var left = $(".submenu");
    var right = $(".product_list");
    if (left.size() > 0) {
        var thisHeight = left.height() + 20;
        var parentHeight = right.height();
        if (thisHeight < parentHeight) {
            left.height(parentHeight);
        }

    }
}

//数字转成ABCD这样的字符
function dToL(d) {
    var str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    return str.charAt(d - 1);
}

//以下产生随机数
rnd.today = new Date();
rnd.seed = rnd.today.getTime();
function rnd() {
    rnd.seed = (rnd.seed * 9301 + 49297) % 233280;
    return rnd.seed / (233280.0);
};
function rand(number) {
    return Math.ceil(rnd() * number);
};

function setCookie(name, value, sec) {
    var expire = sec;
    var exp = new Date();
    exp.setTime(exp.getTime() + expire);
    var domain = "bp.weipass.cn";
    document.cookie = name + "=" + escape(value) + ";domain=" + domain + ";path=/;expires=" + exp.toGMTString();
}
function delCookie(name)//删除cookie
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 9999);
    var cval = getCookie(name);
    if (cval != null) {
        setCookie(name, '', exp);
    }
}
function getCookie(name)//取cookies函数
{
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null) return unescape(arr[2]);
    return null;

}

//阻止冒泡
function stopBubble(e) {
    var e = e ? e : window.event;
    if (window.event) { // IE
        e.cancelBubble = true;
    } else { // FF
        //e.preventDefault();
        e.stopPropagation();
    }
}
//遮和关 显示的z-index必须>2127483647
function openOverlay(str) {
    var body = $("body");
    var overlay = $("#overlay_hmily");
    if (overlay.length <= 0) {
        var overlayNewHeight = document.body.scrollHeight;
        var overlayNewWidth = document.body.scrollWidth;
        var alertDiv = "";
        if (str) {
            var ax = (overlayNewWidth - 640) / 2;
            var ay = (overlayNewHeight - 64) / 2 - 200;
            alertDiv = "<div id='overlay_alert_hmily' class='overlay_alert'>" + str + "</div>"
        }
        body.append("<div id='overlay_hmily' class='overlay_div'></div>" + alertDiv);
        $("#overlay_alert_hmily").css({"left": ax, "top": ay});
        $("#overlay_hmily").height(overlayNewHeight);
    }
}
function closeOverlay() {
    var overlay = $("#overlay_hmily");
    if (overlay.length > 0) {
        overlay.remove();
    }
}

function parentMenu(id) {
    $("#menuRoot").find("li").removeClass("sel");
    $("#" + id).addClass("sel");
}

function highlightMenu(id) {
    $("#menuRoot").find("li").removeClass("sel");
    $("#menuRoot").find("li").removeClass("active");

    $("#" + id).addClass("active");
    $("#" + id).parent().parent().addClass("sel");
}


function hintError(id, msg) {
    $("#" + id + " .ico_ok").remove();
    $("#" + id + " .tips_warning").remove();
    var html = '<div class="tips_warning"><span class="ico_warning"></span>' + msg + '</div>';
    $("#" + id).append(html);
}

function hintOk(id) {
    $("#" + id + " .ico_ok").remove();
    $("#" + id + " .tips_warning").remove();
    var okHtml = '<span class="ico_ok"></span>';
    $("#" + id).append(okHtml);

}


function showHint(type, content) {
    var hintClass = "alert_" + type;
    var hintDiv = '<div class="' + hintClass + '" style="display: none;z-index:999999">' + content + '</div>';
    $("body").append(hintDiv);
    $("." + hintClass).animate(
        {opacity: 'show'}
        , 500
        , function () {
            $("." + hintClass).animate(
                {opacity: 'hide'}
                , 2000
                , function () {
                    $("." + hintClass).remove();
                });
        }
    );
}

function dangerHint(content) {
    showHint('danger', content);
}
function successHint(content) {
    showHint('success', content);
}
