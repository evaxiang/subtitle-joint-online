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
            <form id="loginFrom" method="post" action="${ctx}/index/login" class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-xs-4 control-label">用户名：</label>

                    <div class="col-xs-6 formright" style="margin-top: 6px;">
                        <input id="username" name="username" type="text" placeholder="" required="true"
                               class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="col-xs-4 control-label">登录密码：</label>

                    <div class="col-xs-6 formright">
                        <input id="password" name="password" type="password" placeholder="" required="true"
                               class="form-control">
                    </div>
                </div>
            </form>
            <div class="form-group">
                <div class="col-xs-offset-4 col-xs-4">
                    <button class="btn btn-primary" onclick="login()">登陆</button>
                </div>
                <div class=" col-xs-4">
                    <button class="btn btn-primary" onclick="register()">注册</button>
                </div>
            </div>
        </div>
    </div>

</div>
</body>

<script type="text/javascript">
    $(function () {
/*
        $.post("${ctx}/index/test", {mobile:$(this).val()}, function (data) {
            if (data.status == 1) {
                successHint(data.info);
            }
        },"json");
*/

        var flag = "${requestScope.flag}";
        if(flag == "error"){
            alert("账户名密码错误");
        }
    });

    function login(){
        $('#loginFrom').submit();
    }

    function register(){
        location.href='${ctx}/index/register';
    }

</script>
</html>
