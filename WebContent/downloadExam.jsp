<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="ExamXML.ExamXMLHandler" %>
<%@page import="examDatabase.Exam" %>
<%@page import="examDatabase.ExamDB" %>
<%@page import="userPakage.User" %>
<%@page import="java.io.File" %>
<%@page import="java.io.FileInputStream" %>
<%@page import="javax.servlet.ServletOutputStream" %>
<%
String exam = request.getParameter("ename");
String id = request.getParameter("id");
String number = request.getParameter("number");

if(id!=null)
{
int ID = Integer.valueOf(id);
Exam ex = new Exam(ID);
response.setContentType("application/octet-stream;charset=utf-8");
response.setCharacterEncoding("UTF-8");
response.setHeader("Content-Disposition","attachment;filename="+ex.Title+".xml");
User user = new User();
String fileName = user.md5();
ServletContext context = pageContext.getServletContext();
String location = context.getInitParameter("xml-locationd")+fileName;
ExamXMLHandler exh  = new ExamXMLHandler();
exh.exportExamXML(ID, location);
////out put file to web browser
File file = new File(location);
FileInputStream fileIn = new FileInputStream(file);
ServletOutputStream outst = response.getOutputStream();

byte[] outputByte = new byte[4096];
//copy binary contect to output stream
while(fileIn.read(outputByte, 0, 4096) != -1)
{
	outst.write(outputByte, 0, 4096);
};
fileIn.close();
outst.flush();
outst.close();
};
%>
