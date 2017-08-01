<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="examDatabase.*"%>
<%@page import="userPakage.*"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
<jsp:attribute name="content">
      <h3>Upload Đề:</h3>
      Chọn Đề thi cần tải: <br />
      <form action = "UploadFile.jsp" method = "post"
         enctype = "multipart/form-data">
         <input type = "file" name = "file" size = "50" />
         <br />
         <input type = "submit" value = "Upload File" />
         
      </form>
      </jsp:attribute>
</t:layout>