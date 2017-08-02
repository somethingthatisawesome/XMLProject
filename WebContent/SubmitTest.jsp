<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="java.io.File"%>
<%@page import="userPakage.User" %>
<%@page import="examDatabase.Test" %>
<%@page import="ExamXML.ExamXMLHandler" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String[] checkedIds = request.getParameterValues("answers");
int testID = Integer.valueOf(session.getAttribute("TestID").toString());
User user = (User)session.getAttribute("User");
if(checkedIds!=null)
{
	String file = user.md5();
	String sourceFile = "D:\\XMLProject\\ExamXML\\"+user.username+"_"+testID+".xml";
	String desFile = "D:\\XMLProject\\UserResultXML\\"+file+".xml";
	File source=new File(sourceFile);
	File destination=new File(desFile);
	FileUtils.copyFile(source,destination);
	ExamXMLHandler exh = new ExamXMLHandler();
	exh.exportResult(desFile, checkedIds);
	Test test=  new Test(testID,user.ID,file,exh.getScore(desFile));
	test.insert();
	response.sendRedirect("Result.jsp?test="+file);
}
%>