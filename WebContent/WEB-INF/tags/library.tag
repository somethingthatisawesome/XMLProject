<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="items" fragment="true" %>
 <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Danh sách môn học</h4>
                                <p class="category">Môn học có trong hệ thống</p>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Môn thi</th>
                                    	<th>Số câu hỏi</th>
                                    	<th>Ngày đăng</th>
                                    	<th>Đăng bởi</th>
                                    	<th></th>
                                    	<th></th>
                                    </thead>
                                    <tbody>
                                        <jsp:invoke fragment="items"/>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
<script>
function remove(id)
{
var r = confirm("Xóa Môn học này?");
if(r==true)
	{
xhttp.open("GET", "./DeleteExam.jsp?id="+id, true);
xhttp.send();
Alert("Đã xóa môn học");
location.reload();
	}
}
</script>
