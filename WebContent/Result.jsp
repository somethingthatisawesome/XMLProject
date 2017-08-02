<%@page import="examDatabase.Test" %>
<%@page import="userPakage.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
if(session.getAttribute("User")==null) 
{
	
session.setAttribute("UrlDirect","Result.jsp");
response.sendRedirect("Login.jsp");
return;
};
Test test = new Test();
User user = (User)session.getAttribute("User");
session.setAttribute("Tests", test.findByUserID(user.ID));
%>
<t:layout>
<jsp:attribute name="content">
<t:Result>
<jsp:attribute name="items">
<c:forEach items="${Tests}" var="test">
	<t:ResultItem>
	<jsp:attribute name="ID">${test.ID}</jsp:attribute>
	<jsp:attribute name="title">${test.EXAMTITLE}</jsp:attribute>
	<jsp:attribute name="time">${test.EXAMTIME}</jsp:attribute>
	<jsp:attribute name="date">${test.DATE}</jsp:attribute>
	<jsp:attribute name="mark">${test.MARK}</jsp:attribute>
	
	</t:ResultItem>
</c:forEach>
</jsp:attribute>
</t:Result>
</jsp:attribute>
</t:layout>