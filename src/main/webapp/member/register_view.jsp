<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	input {width: 300px; padding: 10px 20px; margin: 5px 0;}

</style>

</head>
<body>

	<jsp:include page="../default/header.jsp"></jsp:include>
	<div class="wrap">
	<div style="width: 500px; margin: 0 auto;">
		<h1 style="text-align: center;"> 회원가입 페이지 입니다. </h1>
		
		<form action="${contextPath }/member/register.jsp" method="post">
			<input type="text" name="id" placeholder="input id"><br>
			<input type="password" name="pwd" placeholder="input password"><br>
			<input type="text" name="name" placeholder="input name"><br>
			<input type="text" name="addr" placeholder="input address"><br>
			<button type="submit" >register</button><br>		
		</form>
		
	</div>
	</div>
<jsp:include page="../default/footer.jsp"></jsp:include>
</body>
</html>