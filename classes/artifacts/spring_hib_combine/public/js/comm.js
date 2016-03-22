/**
 * Created with IntelliJ IDEA.
 * User: UncleYeee
 * Date: 13-4-26
 * Time: 下午3:45
 * To change this template use File | Settings | File Templates.
 */

/**
 * 高亮菜单
 */
function highlightMenu(){
    $("#nav").find("a").each(function(){
        $(this).removeClass("sel");
    });
    var page = $("#pageName").attr("value");
    $("a[page='"+page+"']").addClass("sel");
}

function closeTipDiv(){
    $(".tips_div").hide();
}


/**
 * LOADING
 */
function newSpin(){
     var w = document.body.clientWidth;
     var h = document.body.clientHeight;
     var screenH = window.screen.availHeight;
    if(h<screenH){
        h = screenH;
    }
     var div ='<div id="spin_target" style="z-index:9999;position:absolute;left: '+0+'px;top:'+80+'px; width: '+w+'px;height:'+h+'px;background:#ccc;opacity:.5; filter: alpha(opacity=50);"></div>';
     $("body").append(div);
     $('#spin_target').spin();
}

function removeSpin(){
   $("#spin_target").remove();
}


/**
 * 初始化上传组件
 * @param id
 * @param swf
 * @param uploader
 * @param imgShow
 * @param imgValue
 */
function initUploader(id,swf,uploader,imgShow,imgValue,imgPreview){

    $('#'+id).uploadify({
        'swf'      : swf,
        'uploader' : uploader,
        'fileObjName':"myimg",
        "buttonText":"上传图片",
        'fileTypeExts' : '*.gif; *.jpg;*.jpeg; *.png',
        'onUploadSuccess' : function(file, data, response) {
            var json = $.parseJSON(data);
            if(json.status > 0){
                $("#button_upload-queue").hide();
                alert('上传成功');
                $("#"+imgShow).attr("src",json.data);//res文件路径
                $("#"+imgShow).show();
                $("#"+imgPreview).attr("src",json.data);//res文件路径
                $("#"+imgValue).attr("value",json.info);// 这里存放了文件名
            }else{
                alert('上传失败，文件过大');
            }

        },
        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
            //alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
            console.log("errorCode:"+  errorCode +", errorMsg:"+errorMsg+",errorString:"+errorString);
            alert('上传失败，文件过大');
        }
    });
}


function previewText(source){
    var text = source.val();
    if(source.is("a")){
       text = source.attr("value");
    }
    var target = source.attr("previewTarget");
    if(text){
        if(source.is("input") &&  text.length >20){
            text = text.substr(0,20) + "...";
        }else if( source.is("textarea") && text.length> 30){
            text = text.substr(0,30) +"...";
        }
        text = text.split("\n").join("<br/>")
    }
    $("#"+target).html(text);
}




function initCrop(id,uploadUrl,cropUrl,imgShow,imgValue){

    $('#'+id).uberuploadcropper({
        //---------------------------------------------------
        // uploadify options..
        //---------------------------------------------------
        fineuploader: {
            //debug : true,
            request	: {
                // params: {},
                endpoint: uploadUrl
            },
            validation: {
                //sizeLimit	: 0,
                allowedExtensions: ['jpg','jpeg','png']
            }
        },
        //---------------------------------------------------
        //now the cropper options..
        //---------------------------------------------------
        jcrop: {
            aspectRatio  : 1,
            allowSelect  : false, //can reselect
            allowResize  : true,  //can resize selection
            setSelect    : [ 0, 0, 200, 200 ], //these are the dimensions of the crop box x1,y1,x2,y2
            minSize      : [ 58, 58 ], //if you want to be able to resize, use these
            maxSize      : [ 300, 300 ]
        },
        //---------------------------------------------------
        //now the uber options..
        //---------------------------------------------------
        //folder           : 'uploads/', // only used in uber, not passed to server
        cropAction       : cropUrl, // server side request to crop image
        onComplete       : function(e,imgs,data){
            var ret = $.parseJSON(data);
            alert(ret.info);
            if(ret.status >0){
                $("#"+imgShow).attr("src",ret.fileurl);//res文件路径
                $("#"+imgShow).show();
                $("#"+imgValue).attr("value",ret.filename);// 这里存放了文件名
                $(".qq-upload-success").hide();
            }
        }
    });







}

/**
 * 打开微博授权窗口
 * @param clientid
 * @param callbackuri
 * @returns {Object}
 */
/*function openWbAuthorizePage(clientid, callbackuri){
    var ret = showModalDialog("https://api.weibo.com/oauth2/authorize?client_id="+clientid+"&response_type=code&redirect_uri="+callbackuri,
        '',
        'dialogHeight:600;dialogWidth:600;dialogLeft:150;');
     alert(ret);
    return ret;
}*/

function openWbAuthorizePage(clientid, callbackuri){
    window.open ("https://api.weibo.com/oauth2/authorize?client_id="+clientid+"&response_type=code&redirect_uri="+callbackuri,
        '绑定微博',
        'height=600,width=600,top=100,left=100,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=yes, status=no')
}



function openWbAuthorizePage2(url){
    var ret = showModalDialog(url,'','dialogHeight:600;dialogWidth:600;dialogLeft:150;');
    alert(ret);
    return ret;
}


/**
 * String转为Int 数组
 * @param str
 * @returns {Array}
 */
function strToIntArray(str) {
    var d = str.split(',');
    var a = new Array();
    for (var i = 0; i < d.length; i++) {
        a[i] = parseInt(d[i]);
    }
    return a;
}

/**
 *  String转为String 数组
 * @param str
 * @returns {Array}
 */
function strToStrArray(str) {
    var d = str.split(',');
    var a = new Array();
    for (var i = 0; i < d.length; i++) {
        a[i] = d[i];
    }
    return a;
}


function currentIsOn(div){
    var mySwitch = $(div).find(".mySwitch");
    return mySwitch.hasClass("switch-on");
}

function toggleSwitch(div){
    var mySwitch = $(div).find(".mySwitch");
    var isOn = mySwitch.hasClass("switch-on");
    var input = $(div).find("input[type='hidden']");
    if(isOn){
        mySwitch.removeClass("switch-on") ;
        mySwitch.addClass("switch-off") ;
        input.val(2);
    }else{
        mySwitch.removeClass("switch-off") ;
        mySwitch.addClass("switch-on") ;
        input.val(1);
    }
}


/**
 *获取指定URL内容填充到渲染区（Tab页切换常用）
 * @param render  渲染区
 * @param url  后台URL
 * @param trigger  触发控件
 */
function loadHtml(render,url,trigger,data){
    render.html("加载中...");
    render.load(url,data);
    if(trigger){
        trigger.siblings().each(function(){
            $(this).removeClass("sel");
        }) ;
        trigger.addClass("sel");
    }
}


function getStringByteLength(val){
    var Zhlength=0;// 全角
    var Enlength=0;// 半角
    for(var i=0;i<val.length;i++){
        if(val.substring(i, i + 1).match(/[^\x00-\xff]/ig) != null){
            Zhlength+=1;
        }else{
            Enlength+=1;
        }
    }
    // 返回当前字符串字节长度
    return (Zhlength*2)+Enlength;
}


function exportExcel(btnId,exportUrl){
     $("#"+btnId).text("导出中...");
     $.get(exportUrl,function(data){
        // alert(data);
         $("#"+btnId).text("导出");
     });
}





