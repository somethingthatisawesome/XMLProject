<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="examDatabase.*"%>
<%@page import="userPakage.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kho dữ liệu câu hỏi</title>
</head>
<body>

<%
ExamDB exam = new ExamDB();
OnlineTest test = new OnlineTest(1);
for(Question q:test.questions)
{
	out.print(q.Value);
	for(Answer a:q.Answers())
	{
		out.print(a.Value);
	}
}
%>
</body>
</html>