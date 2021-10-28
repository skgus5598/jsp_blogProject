<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <c:set var="contextPath" value="${pageContext.request.contextPath }" />   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 로그아웃 페이지 </title>
</head>
<body>
	
	<c:if test="${session_id != null }">	
		<script type="text/javascript">
			alert("${session_id}님 로그아웃 되었습니다.");
			location.href="${contextPath}/default/main.jsp"		
		</script>
		<c:remove var="session_id" scope="session" />
	</c:if>
	<c:if test="${session_id == null }">
		<script>
			location.href="${contextPath}/member/loginPage.jsp"
		</script>
	</c:if>


</body>
</html>