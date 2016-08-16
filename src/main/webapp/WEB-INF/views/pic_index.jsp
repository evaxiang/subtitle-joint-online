<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/commons/import.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>字幕拼接器</title>
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

        /*.img_refresh {*/
            /*animation: mymove 3s;*/
            /*-moz-animation: mymove 3s; /!* Firefox *!/*/
            /*-webkit-animation: mymove 3s; /!* Safari 和 Chrome *!/*/
            /*-o-animation: mymove 3s; /!* Opera *!/*/
            /*animation-iteration-count: infinite;*/
            /*animation-timing-function: linear;*/
            /*height: 50px;*/
            /*width: 50px;*/
        /*}*/

        #img_screen{
            padding-left: 0;
            padding-right: 0;
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
                            <h1 class="page-header">字幕拼接器</h1>

                            <p>无需会PS,一键生成字幕截图,访问 <em>http://subtitle-joint.online/</em></p>
                            <p>知乎:<a href="https://www.zhihu.com/people/an-de-77">https://www.zhihu.com/people/an-de-77</a></p>
                            <p>
                                <a href="http://atdoctor.oss-cn-hangzhou.aliyuncs.com/d6ee2d6c77ac8da23a5d74d07605dca4.png" target="_blank">请回答1988</a>|
                                <a href="http://atdoctor.oss-cn-hangzhou.aliyuncs.com/9d979d84abaaa8ec0f62376615d8bff6.png" target="_blank">纳尼亚传奇3</a>|
                                <a href="http://atdoctor.oss-cn-hangzhou.aliyuncs.com/9bb518a23598aa784bfdf9c7abe3462.png" target="_blank">埃及王子</a>|
                                <a href="http://atdoctor.oss-cn-hangzhou.aliyuncs.com/885ffc79805d6136131460110660b38.png" target="_blank">怦然心动</a>|
                                <a href="http://atdoctor.oss-cn-hangzhou.aliyuncs.com/33e3e98f13772ef561f7d45a0589078f.png" target="_blank">阿甘正传</a>|
                                <a href="http://atdoctor.oss-cn-hangzhou.aliyuncs.com/777fc48f7dd0eb4b1dc0bdff6fe94a5b.png" target="_blank">谁知道这是哪部?</a>|
                                <a href="http://atdoctor.oss-cn-hangzhou.aliyuncs.com/5154d3d72ed34a28ec5bcb42cad07684.png" target="_blank">亿万</a>|
                                <a href="http://atdoctor.oss-cn-hangzhou.aliyuncs.com/4b659e182df07110ad7bf837061383fc.png" target="_blank">聚焦</a>|

                                <a href="http://atdoctor.oss-cn-hangzhou.aliyuncs.com/822991f79f0d1be88a698d6db24dfbc0.png" target="_blank">印度的女儿</a>
                            </p>
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
                                <%--<input id="btn_download" type="button" class="btn btn-primary" value="保存截图"/>--%>

                                <img id="loadingPic" src="${ctx}/public/images/loading.gif" hidden/>
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
                            <%--<div id="div_refresh" hidden><span class="glyphicon glyphicon-repeat img_refresh"></span></div>--%>
                        </div>

                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label">生成截图：</label>

                        <div class="col-xs-10 formright" id="img_screen">
                            <%--<img id="stamp" src="${ctx}/public/images/stamp.png" hidden/>--%>
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
        if(!window.FileReader){
            alert("This Browser doesn't support yet");
        }
    });

    var i = 0;
    var picWidth,picHeight;


    $('#btn_up_url').change(function () {
        var allowUpload;
        var files =  document.getElementById("btn_up_url").files;
        if(files.length > 10){
            if(! confirm("如果上传超过十张，渲染时间会很长，确定要上传吗")){
                return false;
            }
        }

        $.each(files, function (index, file) {
            allowUpload = true;
            i++;
            var MIMEReg = /image\/(png|jpg|jpeg|bmp)/i ;
            if(! MIMEReg.test(file.type)){
                alert("暂时只支持bmp/png/jpg/jpeg格式文件");
                allowUpload = false;
                return false;
            }

            //for log
            $.post("${ctx}/movie/log", {name:file.name,size:file.size,type:file.type}, function () {

            } ,"json");

//            $("#div_refresh").show();
            var fileName = i+"_"+ file.size ;
            $("#img_add").append("<img src='' id='"+fileName+ "' style='display: none;'/>");
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function () {
                var url = reader.result;
                $('#' + fileName).attr('src', url);
                picWidth = $('#' + fileName).width();
                picHeight =$('#' + fileName).height();
                $('#' + fileName).css({"width":"300px"});
                $('#' + fileName).show();
            }
//            $("#div_refresh").hide();
        });
        if(allowUpload) {
            alert("上传完成");
        }
    });




    $("#btn_screen_pic").click(function () {

        if ($("#img_add").children().size() > 1) {
            $("#img_screen").children(":not(#stamp)").remove();
            $("#loadingPic").show();
            $("#stamp").attr("width",picWidth);
            $("#stamp").show();

            alert("渲染中，请等待");
            $("#img_add > img").each(function (i, domElm) {
                if (i == 0) {
                    $("#img_screen").append("<div><img   src='" + $(domElm).attr("src") + "' style='z-index: " + (100 - i) + ";position:relative'></div>");
                } else {
                    var wordHeight;
                    if($("#subTitleHeight").val()){
                        wordHeight = picHeight - $("#subTitleHeight").val();
                    }else{
                        wordHeight = picHeight * (1 - 0.15);
                    }
                    $("#img_screen").append("<div><img   src='" + $(domElm).attr("src") + "' style='z-index: " + (100 - i) + ";margin-top:" + (-wordHeight) + "px;position:relative'></div>");
                }
            });

            html2canvas($("#img_screen"), {
                onrendered: function (canvas) {
                    $("#img_screen").children(":not(#stamp)").remove();
                    $("#img_screen").append(canvas);
                    $("#stamp").hide();
                    alert("渲染完成");

                    var canvas = $("#img_screen > canvas")[0];
                    $.post('${ctx}/movie/upload',
                            {
                                img : canvas.toDataURL("image/jpeg")
                            }, function(data) {
                                console.log(data);
                            },"json");
                },
                allowTaint: true,
                height : $("#img_screen").height(),
                width: picWidth
            });
            $("#loadingPic").hide();

        } else {
            alert("请先上传至少两张图片")
        }
    });

    $("#btn_download").click(function () {

        if ($("#img_add").children(":not(#stamp)").size() > 1) {
            $("#img_screen").children().remove();
            $("#loadingPic").show();
            alert("渲染中，请等待");
            $("#img_add > img").each(function (i, domElm) {
                if (i == 0) {
                    $("#img_screen").append("<div><img   src='" + $(domElm).attr("src") + "' style='z-index: " + (100 - i) + ";position:relative'></div>");
                } else {
                    var wordHeight;
                    if($("#subTitleHeight").val()){
                        wordHeight = picHeight - $("#subTitleHeight").val();
                    }else{
                        wordHeight = picHeight * (1 - 0.15);
                    }
                    $("#img_screen").append("<div><img   src='" + $(domElm).attr("src") + "' style='z-index: " + (100 - i) + ";margin-top:" + (-wordHeight) + "px;position:relative'></div>");
                }
            });

            html2canvas($("#img_screen"), {
                onrendered: function (canvas) {
                    $("#img_screen").children().remove();
                    $("#img_screen").append(canvas);
                    alert("渲染完成");
                    var canvas = $("#img_screen > canvas")[0];
                    $.post('${ctx}/movie/download',
                            {
                                img : canvas.toDataURL("image/jpeg"),
                            }, function(data) {
                                console.log(data);
                            },"json");
                },
                allowTaint: true,
                height : $("#img_screen").height(),
                width: picWidth
            });
            $("#loadingPic").hide();



        } else {
            alert("请先上传至少两张图片")
        }
    });



    $("#btn_refresh").click(function () {
        $("#img_add").children(":not(#div_refresh)").remove();
        $("#img_screen").children(":not(#stamp)").remove();
        i=0;
    });

</script>



</body>
</html>