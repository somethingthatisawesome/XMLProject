<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="ID" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="time" fragment="true" %>
<%@attribute name="date" fragment="true" %>
<%@attribute name="mark" fragment="true" %>
 <tr>
 	<td><jsp:invoke fragment="ID"/></td>
 	<td><jsp:invoke fragment="title"/></td>
 	<td><jsp:invoke fragment="time"/> phút</td>
 	<td><jsp:invoke fragment="date"/></td>
 	<td><jsp:invoke fragment="mark"/></td>
 	<td><a href='Exam.jsp?examID=<jsp:invoke fragment="ID"/>'>Xem chi tiết</a></td>
 </tr>