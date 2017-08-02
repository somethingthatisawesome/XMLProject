<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="examDatabase.*"%>
<%@page import="userPakage.*"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<% 
if(session.getAttribute("User")==null) 
	{
	session.setAttribute("UrlDirect","FileUpload.jsp");
	response.sendRedirect("Login.jsp");
	return;
	};
%>
<t:layout>
<jsp:attribute name="content">
<div class="col-md-8">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Upload đề thi</h4>
                            </div>
                            <div class="content">
      <form action = "UploadFile.jsp" method = "post"
         enctype = "multipart/form-data">
         <div class="form-group">
         <label>Tên đề thi</label>
         <input class="form-control" type = "text" name = "title" size = "50" required/>
         </div>
         <div class="form-group">
         <label>Thời lượng</label>
         <input class="form-control"  type = "number" name = "time" size = "50" required/>
         </div>
         <div class="form-group">
         <label>File cần tải</label>
         <input class="form-control" type = "file" name = "file" size = "50" required/>
         </div>
         <br />
         <input class="btn btn-info btn-fill pull-right" type = "submit" value = "Upload" />
          <div class="clearfix"></div>
      </form>
      </div>
      </div>
      </div>
      
      </jsp:attribute>
</t:layout>