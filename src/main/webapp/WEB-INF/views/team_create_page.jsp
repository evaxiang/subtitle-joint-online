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
            <form id="loginFrom" method="post" action="${ctx}/index/createTeam" class="form-horizontal" role="form">

                <div class="form-group">
                    <label class="col-xs-3 control-label">活动描述：</label>

                    <div class="col-xs-6 formright" style="margin-top: 6px;">
                        <textarea id="details" name="details" type="text" placeholder="" required="true"
                               class="form-control"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label for="date_time" class="col-xs-3 control-label">日期选择：</label>

                    <div class="col-xs-6 formright">
                        <input id="date_time" name="date_time"  class="form-control"
                               placeholder="日期选择" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"  >
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-offset-4 col-xs-4">
                        <button class="btn btn-primary btn-block" type="submit">创建</button>
                    </div>
                </div>

            </form>
        </div>
    </div>

</div>
</body>

<script type="text/javascript">

</script>
</html>
