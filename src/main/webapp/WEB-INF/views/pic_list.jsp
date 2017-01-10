<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 2016/10/24
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/commons/import.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <meta charset="utf-8">
  <title>截图展示</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<div class="container">
  <div class="page-header">
    <h1>所有截图展示<small><a href="${ctx}/movie">返回</a></small></h1>
  </div>

  <div class="row">

      <c:forEach items="${list}" var="item">
        <div class="col-md-4 " style="text-align: center;margin-bottom: 40px" >
          <img class="img-rounded img-responsive center-block" src="${item.img_src}">
          <p style="font-size: medium">创建时间：${item.create_time}</p>
        </div>
      </c:forEach>


  </div>

  <nav style="text-align: center">
    <ul class="pagination ">
      ${page}
    </ul>
  </nav>

</div>



</body>

<script>

  window.onload = function () {
    $("img").each(function (i) {
      var pic = this;
      if(pic.height > 800){
        pic.parentNode.remove();
      }
    })

  };


</script>

</html>
