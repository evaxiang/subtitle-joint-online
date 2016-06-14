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

    <c:forEach items="${list}" var="team">
        <c:if test="${team.list.size() > 0}">
            <div class="row" style="margin-top: 10px;">
                <div class="col-xs-6">
                    <c:forEach items="${team.list}" var="url">
                        <img src="${url}" style="height: 50px;width:auto;">
                    </c:forEach>
                </div>
                <div class="col-xs-6">
                    <c:if test="${isDate == 1 && teamId == null}">
                        <button class="btn btn-primary"
                                onclick="window.location.href = '${ctx}/index/join?teamId=${team.id}&pairId=${pairId}'">
                            加入
                        </button>
                    </c:if>
                    <c:if test="${isDate == 1 && teamId == team.id}">
                        <button class="btn btn-primary"
                                onclick="window.location.href = '${ctx}/index/my_team?teamId=${team.id}'">查看
                        </button>
                    </c:if>

                </div>
            </div>
            <div class="row" style="margin-top: 10px;margin-bottom: 30px">
                <div class="col-xs-6">
                    <td>${team.details}</td>
                </div>
                <div class="col-xs-6">
                    <td>${team.date_Time}</td>
                </div>
            </div>
        </c:if>
    </c:forEach>

    <div class="row" style="margin-top: 20px">
        <c:if test="${isDate == 1 && teamId == null }">
            <button class="btn btn-primary btn-block" onclick="window.location.href = '${ctx}/index/teamDetails'">创建队伍</button>
        </c:if>

    </div>

    <%@ include file="/WEB-INF/views/commons/footer.jsp" %>

</div>
</body>
<script>

</script>

</html>