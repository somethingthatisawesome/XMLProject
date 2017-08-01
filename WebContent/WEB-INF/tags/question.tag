<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="questionTitle" fragment="true" %>
<%@attribute name="questionNumber" fragment="true" %>
<%@attribute name="answers" fragment="true" %>
                    <div class="col-md-12">
                        <div class="card ">
                            <div class="header">
                                <h4 class="title"><jsp:invoke fragment="questionNumber"/></h4>
                                <p class="category"><jsp:invoke fragment="questionTitle"/></p>
                            </div>
                            <div class="content">
                                <div class="table-full-width">
                                    <table class="table">
                                        <tbody>
                                            <jsp:invoke fragment="answers"/>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>