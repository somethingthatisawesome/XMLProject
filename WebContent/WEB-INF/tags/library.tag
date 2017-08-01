<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="items" fragment="true" %>
 <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Thư viện đề</h4>
                                <p class="category">Một số đề thi có trong hệ thống</p>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Môn thi</th>
                                    	<th>Thời lượng</th>
                                    	<th>Ngày đăng</th>
                                    	<th>Đăng bởi</th>
                                    	<th></th>
                                    </thead>
                                    <tbody>
                                        <jsp:invoke fragment="items"/>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>

