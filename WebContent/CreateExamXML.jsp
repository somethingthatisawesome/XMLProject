<%@page import="ExamXML.ExamXMLHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Exam</title>
</head>
<body>
<%
String tempRe = request.getParameter("examID");
if(tempRe!=null)
{
int examID = Integer.parseInt(tempRe);
out.print(examID);
ExamXMLHandler exHandler = new ExamXMLHandler();
exHandler.exportExamXML(examID);
}
else
{
	out.print("Vui lòng chọn đề thi");
}
%>
</body>
</html>