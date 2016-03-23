<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="${ctx}/public/js/jquery-2.1.3.min.js"></script>
    <script src="${ctx}/public/js/verify.js"></script>

    <link href="${ctx}/public/css/style.css" rel="stylesheet">
    <title>Date</title>
</head>
<body>
<div class="container-fluid">
    <table class="table-striped">
        <c:forEach items="list" var="sis">
            <tr>
                <td><img src="http://pic.cnblogs.com/face/111770/20150918082125.png"></td>
                <td>lisa"</td>
                <td>我是lisa"</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>