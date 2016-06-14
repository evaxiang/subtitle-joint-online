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
            <c:forEach items="${list}" var="item">
                <tr>
                    <input id="id" type="hidden" value="${item.id}" />
                    <td>${item.username}</td>
                    <td><img src="${item.img_src}" style="height: 50px;width: auto;"></td>
                    <td>${item.introduction}</td>
                    <td id="like" style="padding-top: 20px">${item.like_num} <i class="glyphicon glyphicon-thumbs-up"></i></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file="/WEB-INF/views/commons/footer.jsp" %>

</div>
</body>
<script>
    $("#like").click(function () {
        $.post("${ctx}/index/like", {from_user_id:${user.id},to_user_id:$(this).siblings("#id").val()},function(result){
            if(result.status == 1){
                alert("修改成功");
                window.location.href= "${ctx}/index/listTeam" ;
            }else{
                alert(result.info);
            }
        },"json");
    });

</script>

</html>