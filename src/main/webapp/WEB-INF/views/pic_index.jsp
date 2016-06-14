<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/commons/import.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>电影截图生成器</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${ctx}/public/css/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctx}/public/css/cropper.css">
    <script src="${ctx}/public/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
    <script src="${ctx}/public/js/jquery.iframe-transport.js" type="text/javascript"></script>
    <script src="${ctx}/public/js/jquery.fileupload.js" type="text/javascript"></script>
    <script src="${ctx}/public/js/cropper.js" type="text/javascript"></script>
    <script src="${ctx}/public/js/dist/html2canvas.js"></script>
    <script type="text/css">
        .center-block {
            display: block;
            margin-left: auto;
            margin-right: auto;
        }
    </script>
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
                            <h1 class="page-header">电影截图生成器</h1>
                            <p>无需会PS，只需要按顺序上传电影截图，一键生成截图</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="btn_up_url" class="col-xs-2 control-label">示例：</label>
                        <div class="col-xs-8 formright">
                            <img src="${ctx}/public/images/example.png" />
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
                                <input id="btn_screen_pic" type="button" class="btn btn-primary" value="生成截图"/>
                            </p>

                            <div id="progress" class="progress">
                                <div class="progress-bar" id="bar" role="progressbar" aria-valuemin="0"
                                     aria-valuemax="100" style="width: 0%;">
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label">图片：</label>

                        <div class="col-xs-4 formright" id="img_add">
                        </div>

                        <label class="col-xs-2 control-label">生成截图：</label>

                        <div class="col-xs-4 formright" id="img_screen">

                        </div>
                    </div>

                    <div class="form-group">

                    </div>

                    <div class="form-group">
                        <!-- 多说评论框 start -->
                        <label class="col-xs-2 control-label">问题反馈：</label>

                        <div class="col-xs-8 formright">
                            <div class="ds-thread" data-thread-key="20160614" data-title="电影截图字幕拼接" data-url="http://112.74.41.252:8080/hib/movie/pic"></div>
                            <!-- 多说评论框 end -->
                            <!-- 多说公共JS代码 start (一个网页只需插入一次) -->
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
                            <!-- 多说公共JS代码 end -->
                        </div>
                    </div>

                </div>
            </form>
        </div>
    </div>

</div>

<script>
    $(function () {
//        html2canvas(document.body, {
//            onrendered: function (canvas) {
//                document.body.appendChild(canvas);
//            },
//            allowTaint: true,
//            width: 300,
//            height: 300
//        });

    });

    var i = 0;
    $('#btn_up_url').fileupload({
        autoUpload: true,//是否自动上传
        url: "${ctx}/file/upload",
        datatype: "text",
        done: function (e, data) {
            i++;
            $("#img_add").append("<img src='' id='headImg" + i + "' style='display: none;'/>");
            var resPath = data.result;
//            $('#img_src').val(resPath);
            $('#headImg' + i).attr('src', resPath);
            $('#headImg' + i).toggle();
            $('#progress #bar').css('width', '0%');
            if(i >= 10){
                alert("目前仅支持上传10张");
                $('#btn_up_url').fileupload('disable');
            }
        },
        progress: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress #bar').css(
                    'width',
                    progress + '%'
            );
            if (progress === 100) {
                alert("已上传，图片较大，请等待服务器处理");
            }
        }
    });

    $("#btn_screen_pic").click(function () {
        if ($("#img_add").children().size() > 1) {
            $("#img_screen").children().remove();

            $("#img_add > img").each(function (i, domElm) {
                if (i == 0) {
                    $("#img_screen").append("<div><img   src='" + $(domElm).attr("src") + "' style='z-index: " + (100 - i) + ";position:relative'></div>");
                } else {
                    var wordHeight = $(domElm).height() * (1 - 0.15);
                    $("#img_screen").append("<div><img   src='" + $(domElm).attr("src") + "' style='z-index: " + (100 - i) + ";margin-top:" + (-wordHeight) + "px;position:relative'></div>");
                }
            });
            alert("截图已生成，请自行截屏");

//            html2canvas(document.body, {
//                onrendered: function (canvas) {
//                    document.body.appendChild(canvas);
//                },
//                allowTaint: true,
//                width: 300,
//                height: 300
//            });
        } else {
            alert("请先上传至少两张图片")
        }
    });

</script>



</body>
</html>