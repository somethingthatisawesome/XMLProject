<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="userPakage.*"%>
    <%@page import="examDatabase.*"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%
String sid = request.getParameter("examID");
if(sid!=null)
{
int id = Integer.valueOf(sid);
ExamDB db = new ExamDB();
Exam exam = new Exam(id);
request.setAttribute("exam", exam);
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tải đề thi</title>
<t:layout>
<jsp:attribute name="content">
 <t:getXML>
 <jsp:attribute name="name">${exam.TITLE}</jsp:attribute>
  <jsp:attribute name="id">${exam.ID}</jsp:attribute>
  <jsp:attribute name="number">${exam.QNUMB}</jsp:attribute>
 </t:getXML>
</jsp:attribute>
</t:layout>