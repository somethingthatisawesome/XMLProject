<%@page import="examManage.*" %>
<%@page import="examDatabase.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String file = request.getParameter("file");
String examTitle = request.getParameter("title");
System.out.println(file);
WordDocument word = new WordDocument("D:\\XMLProject\\FileUploads\\"+file);
ExamDB examDB = new ExamDB();
Exam exam = new Exam(examTitle);
Question question = new Question();
Answer answer = new Answer();
int tempQuestionID=0;
exam.insert();
int Exam_ID = exam.ID;
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