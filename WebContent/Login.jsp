<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <jsp:attribute name="content">
 <div class="col-md-8">
  <div class="card">
  <div class="header">
<h4 class="title">Đăng nhập</h4>
</div>
   <div class="content">
   <form action="/XMLProject/LoginAction.jsp" method="post">
<div class="row">
<div class="col-md-12">
	<div class="form-group">
     <label >Tên đăng nhập</label>
    <input type="text" class="form-control" placeholder="Tên đăng nhập" name="uname" required>
	</div>
</div>
</div>
   <div class="row">
<div class="col-md-12">
    <div class="form-group">
     <label >Mật khẩu</label>
    <input type="password" class="form-control" placeholder="Mật khẩu"   name="psw" required>
</div>
</div>
</div>
<button type="submit" class="btn btn-info btn-fill pull-right">Đăng nhập</button>
 <div class="clearfix"></div>
</form>
</div>
</div>
</div>
    </jsp:attribute>
</t:layout>