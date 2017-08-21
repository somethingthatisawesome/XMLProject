<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="ID" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="time" fragment="true" %>
<%@attribute name="date" fragment="true" %>
<%@attribute name="own" fragment="true" %>
 <tr>
 	<td><jsp:invoke fragment="ID"/></td>
 	<td><jsp:invoke fragment="title"/></td>
 	<td><jsp:invoke fragment="time"/> câu</td>
 	<td><jsp:invoke fragment="date"/></td>
 	<td><jsp:invoke fragment="own"/></td>
 	<td><a href='Exam.jsp?examID=<jsp:invoke fragment="ID"/>'>Kiểm tra</a></td>
 	<td><a href='getXML.jsp?examID=<jsp:invoke fragment="ID"/>'>Tải về</a></td>
 </tr>