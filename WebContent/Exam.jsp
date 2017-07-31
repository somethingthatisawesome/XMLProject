<%@page import="ExamXML.ExamXMLHandler"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="examManage.Paragraph" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đề Thi</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<style>
body
{
background-color: #e0e0e0;
}
body > .container
{
background-color: #ffffff;
}
.answers>*
{
display:block;
}
.answers label
{
	height:20px;
}
.answer *
{
display:inline-block;
}
.answer p
{
padding-left:20px;
}
.questionNumber
{
font:bold;
border-bottom:1px solid #e2e2e2;
}
</style>
</head>
<body>
<div class="container">
<div class="exam">
<%
ServletContext context = pageContext.getServletContext();
String filePath = context.getInitParameter("xml-location");
String tempRe = request.getParameter("examID");
if(tempRe!=null)
{
ExamXMLHandler exHandler = new ExamXMLHandler();
List<Paragraph> prs = exHandler.importExamXML(filePath);
String QID="";
boolean firstQuestion=true;
boolean end=false;
int i=0;
for(Paragraph pr:prs)
{
	
	if(pr.isQuestion)
	{
		i++;
		if(firstQuestion)
		{
			firstQuestion=false;
			
		}
		else
		{
			%>
			</div>
			</div>
			<% 
		}
	%>
	 
	<div class="question col-sm-12">
	<p class="questionNumber">Câu <%out.print(i);%></p>
	<p class="questionTitle">
	<% 
	QID=pr.ID;
	out.print(pr.TextContent);
	%>
	</p>
	<div class="answers">
	<%
	}
	else
	{
		%>
		<div class="answer">
		<input type="radio" name="<% out.print("Question_ID_"+QID);%>">
		
		<p>
		<% 
		out.print(pr.TextContent);
		%>
		</p>
		</div>
		<%
	}
	}
}
else
{
	out.print("Vui lòng chọn đề thi");
} %>
</div>
</div>
</body>
</html>