<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/commons/import.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="${ctx}/public/js/jquery-2.1.3.min.js"></script>
    <script src="${ctx}/public/js/verify.js"></script>

    <title>Date</title>
</head>
<body>
<div class="container">
    <form action="${ctx}/index/dodate" id="dataform">
        <div class="row">
            <input type="hidden" value="${user.id}" name="sis_id"/>

            <div class="col-sm-4 col-sm-offset-4">
                <img class="img-responsive" src="${user.img_src}">
            </div>
        </div>

        <dic class="row">
            <div class="col-sm-4 col-sm-offset-4">
                <p style="text-align: center">${user.username}</p>
            </div>
        </dic>

        <dic class="row">
            <div class="col-sm-4 col-sm-offset-4">
                <h1 style="text-align: center">${user.introduction}</h1>
            </div>
        </dic>

        <dic class="row">
            <div class="col-sm-4 col-sm-offset-4">
                <input class="btn btn-primary btn-block" value="Date" onclick="goto()">
            </div>
        </dic>
    </form>
</div>
</body>
<script>

    function goto() {
        var data = $('#dataform').serialize();
        $("#dataform").find("input[type='submit']").prop({'disabled': true});
        var url = "${ctx}/index/dodate";
        $.ajax({
            type: "post",
            url: url,
            data: data,
            dataType: "json",
            success: function (data) {
                if (data.status == 1) {
                    alert(data.info);
                    setTimeout(function () {
                        window.location.href = "${ctx}/index/listTeam"
                    }, 1000);
                } else {
                    alert(data.info);
                }
            },
            error: function (data) {
                alert('服务端报错，' + data.info);
            }
        });
    }


</script>

</html>