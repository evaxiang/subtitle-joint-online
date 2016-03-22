//全局的ajax访问，处理ajax清求时sesion超时
$.ajaxSetup({
    contentType: "application/x-www-form-urlencoded;charset=utf-8",
    complete: function (XMLHttpRequest, textStatus) {
        //通过XMLHttpRequest取得响应头，sessionstatus
        var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
        if (textStatus == "parsererror") {
            //如果超时就处理 ，指定要跳转的页面
            window.location.href = "${path}/timeout";
        }
    }
});

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


/**
 * require
 * datatype：number email mobile Int,positive, posInt
 * maxlength
 * minlength
 * maxvalue
 * minvalue
 * fraction
 */
/**
 * 对输入框各种情况进行验证
 * @param {Object} btn
 */
function hintInput(input) {
    var datatype = $(input).attr("type");
    var val = $(input).val();
    var placeholder = $(input).attr("placeholder");
    var name = $(input).attr("name");
    var desc = $(input).attr("desc");
    if (!name) {
        return true;
    }
    if (name == "password1") {
        if ($("#password").val() != $("#password1").val()) {
            dangerHint("两次输入的密码不同");
            return false;
        }
    }
    if (!desc) {
        desc = "";
    }

    // 1. require
    var require = $(input).attr('required');
    if (require) {
        if (isNull(val)) {
            //dangerHint(desc + '不能为空哦');
            alert(desc + '不能为空哦');
            return false;
        }
    }
    //2. number
    if (val && datatype == 'number') {
        if (isNaN(val) || val.indexOf(' ') > 0) {
            dangerHint(desc + '不是合法数字');
            return false;
        }
    }

    //3. email
    if (val && datatype == 'email') {
        if (!isEmail(val)) {
            dangerHint(desc + '格式不正确');
            return false;
        }
    }
    //4. mobile
    if (val && datatype == 'mobile') {
        if (!isMobile(val)) {
            dangerHint(desc + '格式不正确');
            return false;
        }
    }
    //4. mobile or tel
    if (val && datatype == 'tel') {
        if (!isMobile(val) && !isTel(val)) {
            dangerHint(desc + '格式不正确');
            return false;
        }
    }
    //5. maxlength/minlength
    var maxlength = $(input).attr('maxlength');
    if (maxlength && val) {
        if (maxlength < val.length) {
            dangerHint(desc + '长度不能超过' + maxlength);
            return false;
        }
    }
    var minlength = $(input).attr('minlength');
    if (minlength && val) {
        if (minlength > val.length) {
            dangerHint(desc + '长度不能少于' + minlength);
            return false;
        }
    }
    //6. maxvalue/minvalue
    var maxvalue = $(input).attr('maxvalue');
    if (maxvalue && val) {
        if (maxvalue - val < 0) {
            dangerHint(desc + '不能大于' + maxvalue);
            return false;
        }
    }
    var minvalue = $(input).attr('minvalue');
    if (minvalue && val) {
        if (minvalue - val > 0) {
            dangerHint(desc + '不能小于' + minvalue);
            return false;
        }
    }
    //7. int
    if (val && datatype == 'int') {
        if (!isPosInt(val)) {
            dangerHint(desc + '必须为整数，如10,12');
            return false;
        }
    }
    //8. positive
    if (val && datatype == 'positive') {
        if (isNaN(val) || val <= 0) {
            dangerHint(desc + '必须大于0的数字，如 1,10');
            return false;
        }
    }
    //9. posInt
    if (val && datatype == 'posInt') {
        if (!isInt(val)) {
            dangerHint(desc + '必须大于0的整数,如 1,10');
            return false;
        }
    }
    //10. 小数点后面多少位
    var fraction = $(input).attr('fraction');
    if (fraction && val && val.indexOf(".") > 0) {
        var len = val.substring(val.indexOf(".") + 1).length;
        if (len > fraction) {
            dangerHint(desc + '小数点后最多输入' + fraction + '位');
            return false;
        }
    }
    return true;

}


/**
 * 提交前验证
 * @param sourceEelement
 * @param isBtn
 * @returns {Boolean}
 */
function confirmSubmit(form) {
    //获取表单的所有input
    var inputs = $(form).find("input,textarea");
    //循环校验
    for (var i = 0; i < inputs.length; i++) {
        var ele = inputs[i];
        var ret = hintInput(ele);
        if (!ret) {
            $(ele).focus();
            return false;
        }
    }
    return true;
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


/**
 * 检测是否为空
 */
function isNull(str) {
    var i;
    if (str.length == 0)
        return true;
    for (i = 0; i < str.length; i++) {
        if (str.charAt(i) != ' ')
            return false;
    }
    return true;
}


/**
 * 小数点后保留2位
 * @param {Object} obj
 */
function checkFraction(val, n) {
    var regu = /^\d+\.\d{1,2}$/;
    return regu.test(val);
}

function isMobile(obj) {
    var reg = /(^[1][0-9]{10}$)/;
    return reg.test(obj);
}

function isEmail(obj) {
    var reg = /^([a-z0-9A-Z]+[_|\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/;
    return reg.test(obj);
}

function isInt(obj) {
    var reg = /^\d+$/;
    return reg.test(obj);
}

function isPosInt(obj) {
    if (!isInt(obj) || obj < 0) {
        return false;
    }
    return true;
}

function isUrl(obj) {
    var reg = /^(https?|http):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|[A-Z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[A-Z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|[A-Z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[A-Z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[A-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[A-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|[A-Z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[A-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|[A-Z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|[A-Z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|[A-Z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|[A-Z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/;
    return reg.test(obj);
}

function isTel(obj) {
    var reg = /^([0-9]{3,4}[-]?)?[0-9]{7,8}([-]?[0-9]{1,9})?$/;
    return reg.test(obj);
}

/**
 * 取得字符串的字节长度
 * @param {Object} str
 */
function strlen(str) {
    var i;
    var len = 0;
    for (i = 0; i < str.length; i++) {
        if (str.charCodeAt(i) > 255) {
            len += 2;
        } else {
            len++;
        }
    }
    return len;
}

function ltrim(str) {
    var whitespace = new String(" \t\n\r");
    var s = new String(str);

    if (whitespace.indexOf(s.charAt(0)) != -1) {
        var j = 0, i = s.length;
        while (j < i && whitespace.indexOf(s.charAt(j)) != -1) {
            j++;
        }
        s = s.substring(j, i);
    }
    return s;
}

function rtrim(str) {
    var whitespace = new String(" \t\n\r");
    var s = new String(str);

    if (whitespace.indexOf(s.charAt(s.length - 1)) != -1) {
        var i = s.length - 1;
        while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1) {
            i--;
        }
        s = s.substring(0, i + 1);
    }
    return s;
}

function trim(str) {
    return rtrim(ltrim(str));
}

function strToStrArray(str) {
    return str.split(",");
}

function strToIntArray(str) {
    if (str && str.trim() != '') {
        var strArr = str.split(",");
        var intArr = Array();
        for (var i = 0; i < strArr.length; i++) {
            intArr[i] = Number(strArr[i]);
        }
        return intArr;
    } else {
        return null;
    }
}

var ieVersion = function () {
    var ver = 100,
        ie = (function () {
            var undef,
                v = 3,
                div = document.createElement('div'),
                all = div.getElementsByTagName('i');
            while (
                div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->',
                    all[0]
                );
            return v > 4 ? v : undef;
        }());
    if (ie) ver = ie;
    return ver;
};