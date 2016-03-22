<%@ page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
 <script type="text/javascript">
 <% if(request.getSession().getAttribute("LOGIN_USER_CODE")==null){%>
	 window.location.href="${ctx}";
 <% }%>
 </script>
