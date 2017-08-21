<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="examDatabase.*"%>
<%@page import="com.google.gson.Gson"%>

<%

String s = request.getParameter("title");
System.out.print(s);
if(s!=null)
{
ExamDB db = new ExamDB();
Exam exam = new Exam();
String json = new Gson().toJson(exam.findByName(s)); // anyObject = List<Bean>, Map<K, Bean>, Bean, String, etc..
response.setContentType("application/json");
response.setCharacterEncoding("UTF-8");
response.getWriter().write(json);
return;
}
%>