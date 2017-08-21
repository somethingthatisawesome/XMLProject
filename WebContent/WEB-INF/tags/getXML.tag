<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="id" fragment="true" %>
<%@attribute name="name" fragment="true" %>
<%@attribute name="number" fragment="true" %>
<div class="col-md-6">
  <div class="card">
  <div class="header">
<h4 class="title">Tải đề thi</h4>
</div>
   <div class="content">
   <form action="/XMLProject/downloadExam.jsp" method="post">
<div class="row">
<div class="col-md-12">
	<div class="form-group">
     <label >Môn học</label>
    <input type="text" class="form-control" placeholder="Môn học" name="ename" value="<jsp:invoke fragment="name"/>" required>
	</div>
</div>
</div>
   <div class="row">
<div class="col-md-12">
    <div class="form-group">
     <label >Số lượng câu hỏi</label>
    <input type="number" class="form-control" placeholder="Số lượng câu hỏi" min="1" max="<jsp:invoke fragment="number"/>"  name="number" required>
</div>
  <input style="display:none;"type="text" name="id" value="<jsp:invoke fragment="id"/>" />
</div>
</div>
<button type="submit" class="btn btn-info btn-fill pull-right">Tải về</button>
 <div class="clearfix"></div>
</form>
</div>
</div>
</div>