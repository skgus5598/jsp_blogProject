<%@page import="com.care.root.member.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>delete.jsp
		id : ${session_id }<br>
		id : ${sessionScope.session_id }<br><!-- 이게 풀네임  -->
		<jsp:useBean id="dao" class="com.care.root.member.dao.MemberDAO" />
		<c:set var="result" value=" ${dao.delete(session_id) }" />		
		<c:choose>
			<c:when test="${result  == 1 }">
					<script> 
						alert("성공적으로 삭제되었습니다.")
						location.href="memberView.jsp";
					</script>
			</c:when>
			<c:otherwise>
					<script> 
						alert("삭제에 실패하였습니다.")
						location.href="member_info.jsp?id=${dto.id}";
					</script>
			</c:otherwise>
	</c:choose>
<%-- 
	<c:remove var="session_id" scope="session" />
	<c:redirect url="memberView.jsp" />
--%>

</body>
</html>