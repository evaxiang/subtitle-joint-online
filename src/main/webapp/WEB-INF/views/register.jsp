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
            <form id="dataform" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="col-xs-4 control-label">用户名：</label>

                    <div class="col-xs-6 formright" style="margin-top: 6px;">
                        <input id="username" name="username" type="text" placeholder="" required="true"
                               class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4 control-label">手机号码：</label>

                    <div class="col-xs-6 formright" style="margin-top: 6px;">
                        <input id="mobile" name="mobile" type="text" placeholder="" required="true"
                               class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="col-xs-4 control-label">登录密码：</label>
                    <div class="col-xs-6 formright">
                        <input id="password" name="password" type="password" placeholder="" required="true"
                               class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label for="newpassword" class="col-xs-4 control-label">确认密码：</label>

                    <div class="col-xs-6 formright">
                        <input id="newpassword" name="newpassword" type="password" placeholder="" required="true"
                               class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4 control-label">我是</label>

                    <div class="col-xs-6 formright">
                        <select id="is_sister" name="is_sister" class="form-control control-label">
                            <option value="0" selected>弟兄</option>
                            <option value="1">姐妹</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-4 control-label">我是</label>

                    <div class="col-xs-6 formright">
                        <select id="is_single" name="is_single" class="form-control control-label">
                            <option value="1" selected>单身</option>
                            <option value="1">学生</option>
                            <option value="0">已婚</option>
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
                                <input id="img_url" type="hidden"/>
                            </span>
                        </p>
                        <img src="" id="headImg" style="display: none"/>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-xs-offset-4 col-xs-4 formright">
                        <input id="registerBtn" type="submit" value="注册" class="btn btn-primary"
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
            $('#img_url').val(resPath);

            alert("头像已上传");
            $('#headImg').attr('src', resPath);
            $('#headImg').toggle();
//            var $image = $('#headImg')
//            var minAspectRatio = 0.5;
//            var maxAspectRatio = 1.5;
//            $image.cropper({
//                built: function () {
//                    var containerData = $image.cropper('getContainerData');
//                    var cropBoxData = $image.cropper('getCropBoxData');
//                    var aspectRatio = cropBoxData.width / cropBoxData.height;
//                    var newCropBoxWidth;
//
//                    if (aspectRatio < minAspectRatio || aspectRatio > maxAspectRatio) {
//                        newCropBoxWidth = cropBoxData.height * ((minAspectRatio + maxAspectRatio) / 2);
//
//                        $image.cropper('setCropBoxData', {
//                            left: (containerData.width - newCropBoxWidth) / 2,
//                            width: newCropBoxWidth
//                        });
//                    }
//                },
//                cropmove: function () {
//                    var cropBoxData = $image.cropper('getCropBoxData');
//                    var aspectRatio = cropBoxData.width / cropBoxData.height;
//
//                    if (aspectRatio < minAspectRatio) {
//                        $image.cropper('setCropBoxData', {
//                            width: cropBoxData.height * minAspectRatio
//                        });
//                    } else if (aspectRatio > maxAspectRatio) {
//                        $image.cropper('setCropBoxData', {
//                            width: cropBoxData.height * maxAspectRatio
//                        });
//                    }
//                }
//            });

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

        if (verifyForm('dataform')) {
            var data = $('#dataform').serialize();
            var url = "${ctx}/index/doRegister";
            $("#registerBtn").val("注册中...");
            $("#registerBtn").prop({disabled: true});
            $.ajax({
                type: "post",
                url: url,
                data: data,
                dataType: "json",
                success: function (data) {
                    if (data.status == 1) {
                        successHint(data.info);
                        setTimeout(function () {
                            window.location.href = "${ctx}/index"
                        }, 1000);
                    } else {
                        dangerHint(data.info);
                        $("#dataform").find("input[type='submit']").prop({'disabled': false});
                    }
                },
                error: function (data) {
                    dangerHint('服务端报错，' + data.status);
                    $("#dataform").find("input[type='submit']").prop({'disabled': false});
                }
            });
            $("#registerBtn").val("注册");
        }
    }

</script>
</html>
