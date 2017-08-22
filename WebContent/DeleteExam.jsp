<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="examDatabase.Exam" %>
<%@page import="userPakage.User" %>
<%
String id = request.getParameter("id");
if(session.getAttribute("User")==null) 
{
session.setAttribute("UrlDirect","index.jsp");
response.sendRedirect("Login.jsp");
return;
};
if(id!=null)
{
User user = (User)session.getAttribute("User");
int ID = Integer.valueOf(id);
Exam exam = new Exam(ID);
exam.remove();
}
response.sendRedirect("index.jsp");
return;
%>