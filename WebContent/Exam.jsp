<%@page import="ExamXML.ExamXMLHandler"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="userPakage.OnlineTest" %>
<%@page import="userPakage.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
  <%
User user = (User) session.getAttribute("User");
if(user==null)
{
	 String url = request.getRequestURL().append('?').append(request.getQueryString()).toString();
	session.setAttribute("UrlDirect",url);
	response.sendRedirect("Login.jsp");
	return;
}
ServletContext context = pageContext.getServletContext();
String filePath = context.getInitParameter("xml-location");
String tempRe = request.getParameter("examID");
session.setAttribute("TestID",tempRe);
if(tempRe!=null)
{
ExamXMLHandler exHandler = new ExamXMLHandler();
int examID = Integer.parseInt(tempRe);
OnlineTest prs = user.takeExam(examID,filePath);
request.setAttribute("OnlineTest", prs);
}
else
{

	response.sendRedirect("ExamLib.jsp");
	return;
}
%>
<t:layout>
<jsp:attribute name="content">
<form action="SubmitTest.jsp" method="post">
<c:forEach items="${OnlineTest.QUESTIONS}" var="question">
<t:question>
	<jsp:attribute name="questionTitle">
	${question.TEXT}
    </jsp:attribute>
    <jsp:attribute name="answers">
	<c:forEach items="${question.ANSWERS}" var="answer">
		<t:answer>
		   <jsp:attribute name="answerContent">
		  	 ${answer.TEXT}
		   </jsp:attribute>
		   <jsp:attribute name="answerID">
		  	 ${answer.ID}
		   </jsp:attribute>
		   </t:answer>
	</c:forEach>
    </jsp:attribute>
</t:question>
</c:forEach>
<center>
<button type="submit" class="btn btn-info btn-fill">Nộp bài kiểm tra</button>
 <div class="clearfix"></div>
</center>
</form>
</jsp:attribute>

</t:layout>

