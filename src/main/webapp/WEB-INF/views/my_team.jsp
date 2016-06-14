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
    <title>登陆</title>
</head>

<body>

<div class="container-fluid">
    <div class="row">
        <div class="panel-body" style="min-height: 520px;">
            <form id="loginFrom" class="form-horizontal" role="form">

                <input type="hidden" value="${id}" name="id" id="id">

                <div class="form-group">
                    <label class="col-xs-3 control-label">活动描述：</label>

                    <div class="col-xs-6 formright" style="margin-top: 6px;">
                        <textarea id="details" name="details" required="true" class="form-control">${details}</textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label for="date_time" class="col-xs-3 control-label">日期选择：</label>

                    <div class="col-xs-6 formright">
                        <input id="date_time" name="date_time" value="${date_time}" class="form-control"
                               placeholder="日期选择" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"  >
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">成员：</label>
                    <div class="col-xs-6 formright">
                        <c:forEach items="${list}" var="item">
                            <img src="${item.img_src}" style="height: 50px;width:auto;">
                        </c:forEach>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-3">
                        <button id="update" class="btn btn-primary btn-block" type="button">修改</button>
                    </div>
                    <div class="col-xs-3">
                        <button id="leave" class="btn btn-warning btn-block" type="button">解散</button>
                    </div>
                </div>

            </form>
        </div>
    </div>

</div>
</body>

<script type="text/javascript">

    $(function() {
        // Handler for .ready() called.
    });


    $("#leave").click(function(){
        <%--$.post("${ctx}/index/")--%>
        alert("暂不支持解散");
    })

    $("#update").click(function(){
        $.post("${ctx}/index/update_my_team", {id:${id},date_time:$("#date_time").val(),details:$("#details").val()},function(result){
            if(result.status == 1){
                alert("修改成功");
                window.location.href= "${ctx}/index/listTeam" ;
            }else{
                alert(result.info);
            }
        },"json");
    })

</script>
</html>
