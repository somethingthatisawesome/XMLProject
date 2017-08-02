<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="java.io.File"%>
<%@page import="userPakage.User" %>
<%@page import="ExamXML.ExamXMLHandler" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String[] checkedIds = request.getParameterValues("answers");
String testID = session.getAttribute("TestID").toString();
User user = (User)session.getAttribute("User");
if(checkedIds!=null)
{
	String sourceFile = "D:\\XMLProject\\ExamXML\\"+user.username+"_"+testID+".xml";
	String desFile = "D:\\XMLProject\\UserResultXML\\"+user.username+"_"+testID+".xml";
	File source=new File(sourceFile);
	File destination=new File(desFile);
	FileUtils.copyFile(source,destination);
	ExamXMLHandler exh = new ExamXMLHandler();
	exh.exportResult(desFile, checkedIds);
	System.out.println(exh.getScore(desFile));
}
%>