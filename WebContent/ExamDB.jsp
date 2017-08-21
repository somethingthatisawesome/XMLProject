<%@page import="examManage.*" %>
<%@page import="examDatabase.*" %>
<%@page import="userPakage.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String examID = request.getParameter("id");
String file = request.getParameter("file");
String examTitle = request.getParameter("title");
System.out.println(file);
User user = (User) session.getAttribute("User");
System.out.println("D:\\XMLProject\\FileUploads\\"+file);
WordDocument word = new WordDocument("D:\\XMLProject\\FileUploads\\"+file);
ExamDB examDB = new ExamDB();
int tempQuestionID=0;
Question question = new Question();
Answer answer = new Answer();
Exam exam;
int Exam_ID;
if(examID==null)
{
exam = new Exam(examTitle,user.ID);
exam.insert();
Exam_ID = exam.ID;
}
else
{
	System.out.println("Exam ID found:"+examID);
	exam = new Exam(Integer.valueOf(examID));
	Exam_ID = exam.ID;
}
System.out.println(exam.ID);
for(Paragraph pr:word.paragraphs)
{
	if(pr.isQuestion==true)
	{
		question.Exam_ID = Exam_ID;
		if(pr.Color!="")
		{
		question.isStatic = true;
		}
		else
		{
			question.isStatic = false;
		}
		question.Value = pr.standardized();
		question.insert();
		tempQuestionID = question.ID;
	}
	else
	{
		answer.Question_ID = tempQuestionID;
		answer.Value = pr.standardized();
		if(pr.Color!="")
		{
			answer.isCorrect = true;
		}
		else
		{
			answer.isCorrect = false;
		}
		answer.insert();
	}
}
response.sendRedirect("index.jsp");
return;
%>