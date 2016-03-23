<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="${ctx}/public/js/jquery-2.1.3.min.js"></script>
    <script src="${ctx}/public/js/verify.js"></script>

    <link href="${ctx}/public/css/style.css" rel="stylesheet">
    <title>注册</title>
</head>

<body>
<%--<img src="${ctx}/public/images/login_banner.png" alt="" class="banner"/>--%>
<div class="container-fluid">
    <form class="" id="registerFrom">
        <div class="row">
            <div class="col-md-4"><label><span class="t_warning">*</span>用户名</label></div>
            <input id="username" name="username" type="text" placeholder="用户名">
            <p>请输入登录名</p>
        </div>

        <div>
            <label><span class="t_warning">*</span> 手机号码</label>
            <input id="mobile" name="mobile" type="text" placeholder="手机号">
            <p>请输入手机号</p>
        </div>
        <div>

        </div>

        <div>
            <label><span class="t_warning">*</span> 登录密码</label>
            <input id="password" name="password" type="password" placeholder="登录密码">

            <p>请输入6位或6位以上的数字、字母</p>
        </div>
        <div>
            <label><span class="t_warning">*</span> 确认密码</label>
            <input id="newpassword" type="password" placeholder="确认密码">
        </div>

        <div><input id="registerBtn" type="button" value="注册" class="btn_block btn_primary" onclick="register()"></div>
    </form>
</div>
</body>

<script type="text/javascript">
    $(function () {

    });

    function register() {
        var pass = $("#password").val();
        var newpass = $("#newpassword").val();

        if (pass != newpass) {
            alert("两次密码不一致!");
            return;
        }

        if (pass.length < 6) {
            alert("登录密码不能少于6位！");
            return;
        }

        if (verifyForm('registerFrom')) {
            var fdata = $('#registerFrom').serialize();
            var url = "${ctx}/index/register";
            $("#registerBtn").val("注册中...");
            $("#registerBtn").prop({disabled: true});
            $.ajax({
                url: url,
                type: "POST",
                data: fdata,
//        dataType: "json",
                success: function (data) {
                    url = "${ctx}/index/loginPage";
                    window.location.href = url;
                },
                error: function (data) {
                    alert('服务端报错' + data.status);
                    $("#registerBtn").val("马上注册");
                    $("#registerBtn").prop({disabled: false});
                }

            });
        }

    }

</script>
</html>
