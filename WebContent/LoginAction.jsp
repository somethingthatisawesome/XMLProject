<%@page import="userPakage.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String username = request.getParameter("uname");
String password = request.getParameter("psw");
User user = new User();
if(user.Login(username, password)==true)
{
	
	session.setAttribute("User", user);
	
	User a = (User) session.getAttribute("User");
	
	response.sendRedirect("index.jsp");
}
else
{
	System.out.print("Không thể đăng nhập!");
	response.sendRedirect("Login.jsp");
}

%>
</body>
</html>