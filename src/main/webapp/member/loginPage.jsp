<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <c:set var="contextPath" value="${pageContext.request.contextPath }" />   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 로그인 페이지 </title>
<style>
.wrap{ text-align: center; }
table { padding-left: 500px;}

</style>

</head>
<body>
<jsp:include page="../default/header.jsp"></jsp:include>
<div class="wrap">
<br>
<h1>로그인 페이지 입니다</h1><br>

<form action="${contextPath }/member/loginConfirm.jsp" method="post">
<table>
	<tr>
		<td><input type="text" name="id" placeholder="아이디" ></td>
		<td rowspan="2"><input type="submit" value="로그인"> </td>
	</tr>
	<tr>
		<td><input type="text" name="pwd" placeholder="비밀번호" ></td>
	</tr>
</table>
</form>
</div>




<jsp:include page="../default/footer.jsp"></jsp:include>
</body>
</html>