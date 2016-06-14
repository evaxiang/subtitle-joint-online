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
    <link rel="stylesheet" href="${ctx}/public/css/cropper.css">
    <script src="${ctx}/public/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
    <script src="${ctx}/public/js/jquery.iframe-transport.js" type="text/javascript"></script>
    <script src="${ctx}/public/js/jquery.fileupload.js" type="text/javascript"></script>
    <script src="${ctx}/public/js/cropper.js" type="text/javascript"></script>

    <title>注册</title>
</head>

<body>

<div class="container-fluid">
    <div class="row">
        <div class="panel-body" style="min-height: 520px;">
            <form id="registerFrom" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">

                <input type="hidden" name="id" id="id" value="${user.id}" />

                <div class="form-group">
                    <label class="col-xs-4 control-label">用户名：</label>

                    <div class="col-xs-6 formright" style="margin-top: 6px;">
                        <input id="username" name="username" type="text" placeholder="" required="true" value="${user.username}"
                               class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4 control-label">一句话简介：</label>

                    <div class="col-xs-6 formright" style="margin-top: 6px;">
                        <input id="introduction" name="introduction" type="text" placeholder="" value="${user.introduction}"
                               class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4 control-label">手机号码：</label>

                    <div class="col-xs-6 formright" style="margin-top: 6px;">
                        <input id="mobile" name="mobile" type="text" placeholder="" required="true" value="${user.mobile}"
                               class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="col-xs-4 control-label">登录密码：</label>
                    <div class="col-xs-6 formright">
                        <input id="password" name="password" type="password" placeholder="" required="true" value="${user.password}"
                               class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label for="newpassword" class="col-xs-4 control-label">确认密码：</label>

                    <div class="col-xs-6 formright">
                        <input id="newpassword" name="newpassword" type="password" placeholder="" required="true" value="${user.password}"
                               class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4 control-label">我是</label>

                    <div class="col-xs-6 formright">
                        <select id="is_sister" name="is_sister" class="form-control control-label">
                            <option value="0" <c:if test="${user.is_sister} == 0">selected</c:if> selected>弟兄</option>
                            <option value="1" <c:if test="${user.is_sister} == 1">selected</c:if> >姐妹</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4 control-label">我是</label>

                    <div class="col-xs-6 formright">
                        <select id="is_single" name="is_single" class="form-control control-label">
                            <option value="0" <c:if test="${user.is_single} == 0">selected</c:if> 学生</option>
                            <option value="1" <c:if test="${user.is_single} == 1">selected</c:if> selected>单身</option>
                            <option value="2" <c:if test="${user.is_single} == 2">selected</c:if> >已婚</option>
                        </select>
                    </div>
                </div>


                <div class="form-group">
                    <label for="btn_up_url" class="col-xs-4 control-label">头像上传：</label>
                    <input type="hidden" id="img_src" name="img_src" >

                    <div class="col-xs-6 formright">
                        <p>
                <span class="btn btn-default fileinput-button">
                <span>上传</span>
                <input id="btn_up_url" type="file" name="myFile" multiple/>
                </span>
                        </p>
                        <img src="" id="headImg" style="display: none"/>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-xs-offset-4 col-xs-4 formright">
                        <input id="registerBtn" type="button" value="保存" class="btn btn-primary"
                               onclick="register()">
                    </div>
                </div>

            </form>
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
        datatype: "text",
        done: function (e, data) {
            var resPath = data.result;
            $('#img_src').val(resPath);
            alert("头像已上传");
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
            var url = "${ctx}/index/modifyUser";
            $("#registerBtn").val("修改...");
            $("#registerBtn").prop({disabled: true});
            $.ajax({
                url: url,
                type: "POST",
                data: fdata,
//        dataType: "json",
                success: function (data) {
                    alert("修改成功，请重新登录");
                    url = "${ctx}/index/loginPage";
                    window.location.href = url;
                },
                error: function (data) {
                    alert('服务端报错' + data.status);
                    $("#registerBtn").val("修改");
                    $("#registerBtn").prop({disabled: false});
                }

            });
        }
    }

</script>
</html>
