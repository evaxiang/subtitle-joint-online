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
    <div class="row">
        <table class="table table-striped">
            <thead>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="sis">
                <tr>
                    <td>${sis.username}</td>
                    <td><img src="${sis.img_src}" style="height: 50px;width:50px;"></td>
                    <td>${sis.mobile}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row">
        <div class="col-xs-4 col-xs-offset-3">
            <input class="btn btn-primary btn-lg" type="button" onclick="exitTeam()" value="退出队伍">
        </div>
        <div class="col-xs-4">
            <input class="btn btn-primary btn-lg" type="button" onclick="window.location.href='${ctx}/index/listTeam'" value="返回">
        </div>
    </div>

</div>
</body>
<script>
    function exitTeam(){
        if(confirm("你确定要退出队伍？")){
            $.get("${ctx}/index/")
        }
    }

</script>

</html>