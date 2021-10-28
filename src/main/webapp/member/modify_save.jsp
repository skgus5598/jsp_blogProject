<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
	%>
	<jsp:useBean id="dto"  class="com.care.root.member.dto.MemberDTO" />
	<jsp:setProperty property="*" name="dto" />
	
	<jsp:useBean id="dao" class="com.care.root.member.dao.MemberDAO" />
	<c:choose>
		<c:when test="${dao.modify_save(dto)  == 1 }">
				<script> 
					alert("성공적으로 수정되었습니다.")
					location.href="member_info.jsp?id=${dto.id}";
				</script>
		</c:when>
		<c:otherwise>
				<script> 
					alert("수정에 실패하였습니다.")
					location.href="modify.jsp?id=${dto.id}";
				</script>
		</c:otherwise>
	</c:choose>
	
	

	

</body>
</html>