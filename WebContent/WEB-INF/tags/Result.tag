<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="items" fragment="true" %>
 <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Kết quả thi</h4>
                                <p class="category">Các bài thi đã thực hiện</p>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Môn thi</th>
                                    	<th>Thời lượng</th>
                                    	<th>Ngày làm bài</th>
                                    	<th>Điểm</th>
                                    </thead>
                                    <tbody>
                                        <jsp:invoke fragment="items"/>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>

