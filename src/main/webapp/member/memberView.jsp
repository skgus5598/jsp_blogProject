<%@page import="com.care.root.member.dao.MemberDAO"%>
<%@page import="com.care.root.member.dto.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
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
<jsp:include page="../default/header.jsp"></jsp:include>
	
<div class="wrap">
<%-- 
	<%
			MemberDAO dao = new MemberDAO();
			ArrayList<MemberDTO> list = dao.memberView();	
			for(MemberDTO d : list){ 
				out.print(d.getId() + ", ");
				out.print(d.getPwd() + ", ");
				out.print(d.getName() + ", ");
				out.print(d.getAddr() + ", ");
			}
	 %>
--%>
<!-- 위와 동일한 코드! 다만 테이블을 만들어 넣은  -->
<jsp:useBean id="dao" class="com.care.root.member.dao.MemberDAO" /><!-- 이건 객체 선언  -->
<c:set var="list" value="${dao.memberView() }" /><!-- 이건 변수만들어서 값 담는거  -->
<table border='1'>
	<tr>
		<th>아이디</th><th>비밀번호</th><th>이름</th><th>주소</th>
	</tr>
	<c:forEach var="d" items="${list }">
	<tr>
		<td>${d.id }</td>
		<td>${d.pwd }</td>
		<td>
			<a href="${contextPath }/member/member_info.jsp?id=${d.id }">	${d.name } </a>
		</td>
		<td>${d.addr }</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan='4' align="right">
			<a href="${contextPath }/member/register_view.jsp">회원가입</a>
		</td>
	</tr>
</table>

</div>

	

<jsp:include page="../default/footer.jsp"></jsp:include>
</body>
</html>












