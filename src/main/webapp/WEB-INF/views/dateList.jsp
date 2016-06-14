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
                    <td><img src="${sis.img_src}" style="height: 50px;width: auto;"></td>
                    <td>${sis.introduction}</td>
                    <c:if test="${isDate != 1 }">
                        <td>
                            <button class="btn btn-primary"  onclick="window.location.href = '${ctx}/index/date/${sis.id}'">约Date</button>
                        </td>
                    </c:if>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file="/WEB-INF/views/commons/footer.jsp" %>

</div>
</body>
<script>

</script>

</html>