<%@ page import="com.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="row" style="margin-top: 30px">
    <div class="col-xs-3">
        <c:if test="${user.is_sister == 0}">
            <btn class="btn btn-primary btn-block" onclick="window.location.href = '${ctx}/index/listSister'">姐妹</btn>
        </c:if>
        <c:if test="${user.is_sister == 1}">
            <btn class="btn btn-primary btn-block" onclick="window.location.href = '${ctx}/index/listBrother'">弟兄</btn>
        </c:if>
    </div>
    <div class="col-xs-3">
        <btn class="btn btn-primary btn-block" onclick="window.location.href = '${ctx}/index/listTeam'">队伍</btn>
    </div>
    <div class="col-xs-3">
        <btn class="btn btn-primary btn-block" onclick="window.location.href = '${ctx}/index/info/${user.id}'">我的信息</btn>
    </div>
    <div class="col-xs-3">
        <btn class="btn btn-primary btn-block" onclick="window.location.href = '${ctx}/index/logout/${user.id}'">注销</btn>
    </div>
</div>
