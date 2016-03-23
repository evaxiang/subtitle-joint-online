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
            <form id="registerFrom" method="post" class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-3 control-label">用户名：</label>

                    <div class="col-sm-6 formright" style="margin-top: 6px;">
                        <input id="username" name="username" type="text" placeholder="" required="true"
                               class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">手机号码：</label>

                    <div class="col-sm-6 formright" style="margin-top: 6px;">
                        <input id="mobile" name="mobile" type="text" placeholder="" required="true"
                               class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="col-sm-3 control-label">登录密码：</label>

                    <div class="col-sm-6 formright">
                        <input id="password" name="password" type="password" placeholder="" required="true"
                               class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label for="newpassword" class="col-sm-3 control-label">确认密码：</label>

                    <div class="col-sm-6 formright">
                        <input id="newpassword" name="newpassword" type="password" placeholder="" required="true"
                               class="form-control">
                    </div>
                </div>


                <div class="form-group">
                    <label for="btn_up_url" class="col-sm-3 control-label">图片上传：</label>

                    <div class="col-sm-6 formright">
                        <p>
                            <span class="btn btn-default">
                                <span>上传</span>
                                <input id="btn_up_url" type="file" name="myFile" multiple/>
                            </span>
                        </p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">下载url：</label>

                    <div class="col-sm-6 formright">
                        <input id="v_url" name="v_url" type="text" class="form-control" readonly>
                    </div>
                </div>

                <div class="form-group">
                    <label for="length" class="col-sm-3 control-label">文件大小：</label>

                    <div class="col-sm-6 formright">
                        <input id="length" name="length" type="text" class="form-control" readonly>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9 formright">
                        <input id="registerBtn" type="button" value="注册" class="btn_block btn_primary"
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
        dataType: 'json',
        done: function (e, data) {
            var json = data.result;
            if (json.status == 1) {
                var obj = json.data;
                var resPath = obj.v_url;
                alert("url:"+resPath);
            } else {
                dangerHint(json.info);
                return false;
            }
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
