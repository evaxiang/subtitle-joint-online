<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/commons/import.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>电影截图字幕生成器</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${ctx}/public/css/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctx}/public/css/cropper.css">
    <script src="${ctx}/public/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
    <script src="${ctx}/public/js/jquery.iframe-transport.js" type="text/javascript"></script>
    <script src="${ctx}/public/js/jquery.fileupload.js" type="text/javascript"></script>
    <script src="${ctx}/public/js/cropper.js" type="text/javascript"></script>
    <script src="${ctx}/public/js/dist/html2canvas.js"></script>
    <style>
        .center-block {
            display: block;
            margin-left: auto;
            margin-right: auto;
        }

        .img_refresh {
            animation: mymove 3s;
            -moz-animation: mymove 3s; /* Firefox */
            -webkit-animation: mymove 3s; /* Safari 和 Chrome */
            -o-animation: mymove 3s; /* Opera */
            animation-iteration-count: infinite;
            animation-timing-function: linear;
            height: 50px;
            width: 50px;
        }
        #img_screen{
            padding-left: 0;
            padding-right: 0;
        }

        @keyframes mymove {
            0% {
                transform: rotate(0deg);
                -ms-transform: rotate(0deg); /* IE 9 */
                -moz-transform: rotate(0deg); /* Firefox */
                -webkit-transform: rotate(0deg); /* Safari 和 Chrome */
                -o-transform: rotate(0deg); /* Opera */
            }
            100% {
                transform: rotate(360deg);
                -ms-transform: rotate(360deg); /* IE 9 */
                -moz-transform: rotate(360deg); /* Firefox */
                -webkit-transform: rotate(360deg); /* Safari 和 Chrome */
                -o-transform: rotate(360deg);
            }
        }

        @-webkit-keyframes mymove /* Safari and Chrome */
        {
            0% {
                transform: rotate(0deg);
                -ms-transform: rotate(0deg); /* IE 9 */
                -moz-transform: rotate(0deg); /* Firefox */
                -webkit-transform: rotate(0deg); /* Safari 和 Chrome */
                -o-transform: rotate(0deg); /* Opera */
            }
            100% {
                transform: rotate(360deg);
                -ms-transform: rotate(360deg); /* IE 9 */
                -moz-transform: rotate(360deg); /* Firefox */
                -webkit-transform: rotate(360deg); /* Safari 和 Chrome */
                -o-transform: rotate(360deg);
            }
        }

        .form-group > div {
            margin-top: 7px;
        }

    </style>
</head>
<body>
<div class="container-fluid">

    <div class="row">
        <div class="panel-body" style="min-height: 520px;">


            <form id="registerFrom" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                <div class=" center-block">
                    <div class="form-group">
                        <label class="col-xs-2 control-label"></label>

                        <div class="col-xs-8 formright">
                            <h1 class="page-header">电影截图字幕生成器</h1>

                            <p>无需会PS，一键生成字幕截图(目前最多限十张截图)</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="btn_up_url" class="col-xs-2 control-label">操作：</label>

                        <div class="col-xs-4 formright">
                            <p>
                                <span class="btn btn-default fileinput-button">
                                    <span>上传</span>
                                    <input id="btn_up_url" type="file" name="myFile" multiple/>
                                </span>
                                <input id="btn_refresh" class="btn btn-warning" type="button" value="重新上传"/>
                            </p>

                            <div class="input-group col-xs-12">
                                <input id="subTitleHeight" name="subTitleHeight" type="number"
                                       placeholder="设置字幕高度(默认为高度*0.15)" class="form-control">
                                <span class="input-group-addon">px</span>
                                <input id="btn_screen_pic" type="button" class="btn btn-primary" value="生成截图"/>
                            </div>
                        </div>
                    </div>

                    <%--<div class="form-group">--%>
                        <%--<label for="btn_up_url" class="col-xs-2 control-label"></label>--%>

                        <%--<div class="col-xs-4 formright">--%>
                            <%--<div id="progress" class="progress">--%>
                                <%--<div class="progress-bar" id="bar" role="progressbar" aria-valuemin="0"--%>
                                     <%--aria-valuemax="100" style="width: 0%;">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                    <%--</div>--%>

                    <div class="form-group">
                        <label class="col-xs-2 control-label">图片：</label>

                        <div class="col-xs-10 formright" id="img_add">
                            <div id="div_refresh" hidden><span class="glyphicon glyphicon-repeat img_refresh"></span></div>
                        </div>

                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label">生成截图：</label>

                        <div class="col-xs-10 formright" id="img_screen">

                        </div>
                    </div>

                    <%--<div class="form-group">--%>
                        <%--<label for="btn_up_url" class="col-xs-2 control-label">示例：</label>--%>

                        <%--<div class="col-xs-8 formright">--%>
                            <%--<img src="${ctx}/public/images/example.png"/>--%>
                        <%--</div>--%>

                    <%--</div>--%>

                    <div class="form-group">
                        <label class="col-xs-2 control-label">问题反馈：</label>

                        <div class="col-xs-8 formright">
                            <div class="ds-thread" data-thread-key="20160614" data-title="电影截图字幕拼接"
                                 data-url="http://112.74.41.252:8080/hib/movie/pic"></div>
                            <script type="text/javascript">
                                var duoshuoQuery = {short_name: "movie-screenshot-join"};
                                (function () {
                                    var ds = document.createElement('script');
                                    ds.type = 'text/javascript';
                                    ds.async = true;
                                    ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
                                    ds.charset = 'UTF-8';
                                    (document.getElementsByTagName('head')[0]
                                    || document.getElementsByTagName('body')[0]).appendChild(ds);
                                })();
                            </script>
                        </div>
                    </div>

                </div>
            </form>
        </div>
    </div>

</div>

<script>
    $(function () {

    });

    var i = 0;
    $('#btn_up_url').fileupload({
        datatype: "json",
        done: function (e, data) {
            i++;
            $("#div_refresh").show();
            if (i >= 10) {
                alert("目前仅支持上传10张");
                $('#btn_up_url').fileupload('disable');
            }
            var file =  data.files[0];
            var fileName = i+"_"+ file.size ;
            $("#img_add").append("<img src='' id='"+fileName+ "' style='display: none;'/>");
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function () {
                var url = reader.result;
                $('#' + fileName).attr('src', url);
                $('#' + fileName).show();
            }
            $("#div_refresh").hide();
        }
    });




    $("#btn_screen_pic").click(function () {

        if ($("#img_add").children().size() > 1) {
            $("#img_screen").children().remove();

            $("#img_add > img").each(function (i, domElm) {
                if (i == 0) {
                    $("#img_screen").append("<div><img   src='" + $(domElm).attr("src") + "' style='z-index: " + (100 - i) + ";position:relative'></div>");
                } else {
                    var wordHeight;
                    if($("#subTitleHeight").val()){
                        wordHeight = $(domElm).height() - $("#subTitleHeight").val();
                    }else{
                        wordHeight = $(domElm).height() * (1 - 0.15);
                    }
                    $("#img_screen").append("<div><img   src='" + $(domElm).attr("src") + "' style='z-index: " + (100 - i) + ";margin-top:" + (-wordHeight) + "px;position:relative'></div>");
                }
            });
            alert("截图已生成,点击右键保存");
            html2canvas($("#img_screen"), {
                onrendered: function (canvas) {
                    $("#img_screen").children(":not(canvas)").remove();
                    $("#img_screen").append(canvas);
                },
                allowTaint: true,
                htight : $("#img_screen").height(),
                width: $("#img_add > img").first().width()
            });
        } else {
            alert("请先上传至少两张图片")
        }
    });

    $("#btn_refresh").click(function () {
        $("#img_add").children(":not(#div_refresh)").remove();
        $("#img_screen").children().remove();
        i = 0 ;
    });

</script>



</body>
</html>