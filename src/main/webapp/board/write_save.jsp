<%@page import="com.care.root.board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

 <c:set var="contextPath" value="${pageContext.request.contextPath }" />   
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../default/header.jsp"></jsp:include>
<fmt:requestEncoding value="utf-8"/>
	<% 
			request.setCharacterEncoding("utf-8");
			BoardDAO dao = new BoardDAO();
			dao.writeSave(request.getParameter("name"),
									  request.getParameter("title"),
									  request.getParameter("content"));
			response.sendRedirect("list.jsp");			
	%>
<%-- 
	<jsp:useBean id="dao" class="com.care.root.board.dao.BoardDAO"/>
	${dao.writeSave(param.name, param.title, param.content) }
	<c:redirect url="list.jsp"/>
--%>

<jsp:include page="../default/footer.jsp"></jsp:include>
</body>
</html>