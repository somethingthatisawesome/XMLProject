<%@page import="examDatabase.*"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
ExamDB db = new ExamDB();
Exam ex = new Exam();
request.setAttribute("ExamLib", ex);
%>
<t:layout>
<jsp:attribute name="content">
<t:library>
<jsp:attribute name="items">
<c:forEach items="${ExamLib.ALL}" var="ex">
	<t:libItem>
	<jsp:attribute name="ID">${ex.ID}</jsp:attribute>
	<jsp:attribute name="title">${ex.TITLE}</jsp:attribute>
	<jsp:attribute name="time">${ex.QNUMB}</jsp:attribute>
	<jsp:attribute name="date">${ex.DATE}</jsp:attribute>
	<jsp:attribute name="own">${ex.USERNAME}</jsp:attribute>
	</t:libItem>
</c:forEach>
</jsp:attribute>
</t:library>
</jsp:attribute>
</t:layout>