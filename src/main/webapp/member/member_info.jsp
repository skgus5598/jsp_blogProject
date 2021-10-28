<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
 <c:set var="contextPath" value="${pageContext.request.contextPath }" />   
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="dao" class="com.care.root.member.dao.MemberDAO" />
<c:set var="mem" value="${dao.memberInfo(param.id) }" />


<jsp:include page="../default/header.jsp"></jsp:include>
<div class="wrap">
	<c:choose>
		<c:when test="${session_id != null }">
			<h1>개인정보를 보여줍니다 .</h1>
			${param.id }, <%=request.getParameter("id") %>		
			<hr>
			id : ${mem.id }<br>
			pwd : ${mem.pwd }<br>
			name : ${mem.name }<br>
			addr : ${mem.addr }<br>
			<hr>			
				<c:if test="${session_id == mem.id }">
					<button type="button" onclick="location.href='modify.jsp?id=${mem.id }'">
						수정
					</button>
					<c:set var="session_id" value="${mem.id }" scope="session"/>
					<button type="button" onclick="location.href='delete.jsp'">삭제</button>
				</c:if>					
		</c:when>
		<c:otherwise>
			<script>
				alert("로그인 먼저 해주세요!");
				location.href="${contextPath}/member/loginPage.jsp"
			</script>
		</c:otherwise>
	</c:choose>

		
</div>

<jsp:include page="../default/footer.jsp"></jsp:include>
</body>
</html>