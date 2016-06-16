<%@ page import="com.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="row" style="margin-top: 30px">

    <div class="btn-group btn-group-lg btn-group-justified" role="group">

        <div class="btn-group">
            <c:if test="${user.is_sister == 0}">
                <button type="button" class="btn btn-default" onclick="window.location.href = '${ctx}/index/listSister'">姐妹</button>
            </c:if>
            <c:if test="${user.is_sister == 1}">
                <button type="button" class="btn btn-default" onclick="window.location.href = '${ctx}/index/listBrother'">弟兄</button>
            </c:if>
        </div>
        <div class="btn-group">
            <button class="btn btn-default" type="button" onclick="window.location.href = '${ctx}/index/listTeam'">队伍</button>
        </div>
        <div class="btn-group">
            <button class="btn btn-default" type="button" onclick="window.location.href = '${ctx}/index/info/${user.id}'">我的信息</button>
        </div>
        <div class="btn-group">
            <button class="btn btn-default" type="button" onclick="window.location.href = '${ctx}/index/logout/${user.id}'">注销</button>
        </div>
    </div>

</div>
