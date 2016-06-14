<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/commons/import.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${ctx}/public/css/jquery.fileupload.css">
    <script src="${ctx}/public/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
    <script src="${ctx}/public/js/jquery.iframe-transport.js" type="text/javascript"></script>
    <script src="${ctx}/public/js/jquery.fileupload.js" type="text/javascript"></script>
    <title>注册</title>
</head>

<body>

<div class="container-fluid">
    <div class="row">
        <div class="panel-body" style="min-height: 520px;">

            <div class="form-group">
                <div class="col-sm-6 formright" style="margin-top: 6px;">
                    <img src="" />
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    $(function () {

    });

    $('#btn_up_url').fileupload({
        autoUpload: true,//是否自动上传
        url: "${ctx}/file/upload",
        done: function (url) {
            alert(">>>>>>>>");
            var resPath = url;
        }
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
