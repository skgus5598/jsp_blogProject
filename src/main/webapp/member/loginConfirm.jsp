<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 로그인 검증 페이지 </title>
</head>
<body>

<jsp:useBean id="dao" class="com.care.root.member.dao.MemberDAO"/>
<jsp:useBean id="dto" class="com.care.root.member.dto.MemberDTO"/>
<c:set var="result"  value="${dao.loginConfirm(param.id, param.pwd) }"/>

<c:choose>
	<c:when test="${result == 1 }">
		<c:set var="session_id" value="${param.id }" scope="session"/>
		<script>
				alert(" 로그인 성공! 세션값 : ${session_id} ")
				location.href="../default/main.jsp"
		</script>
	</c:when>
	<c:when test="${result == 0 }">
		<script>
				alert(" 비밀번호가 틀렸습니다! ")
				location.href="loginPage.jsp";
			</script>	
	</c:when>	
	<c:otherwise>
		<script>
			alert(" 존재하지 않는 아이디입니다. ")
			location.href="loginPage.jsp";
		</script>
	</c:otherwise>
	
</c:choose>

</body>
</html>