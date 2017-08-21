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
<div class="col-md-6" ng-app="myApp" >
                        <div class="card" ng-controller="someCtrl">
                            <div class="header">
                                <h4 class="title">Upload câu hỏi</h4>
                            </div>
                            <div class="content">
      <form action = "UploadFile.jsp" method = "post" autocomplete="off"
         enctype = "multipart/form-data">
         <div class="form-group">
         <label>Môn học</label>
         <input  class="form-control" type = "text" name = "title" size = "50" ng-model="keywords" ng-change="search()" required/>
         </div>
         <ul class="search-dropdown">
         <li ng-repeat="x in response"><a href="#"  ng-click="setExamID(x.ID,x.Title)">{{x.Title}}</a></li>
         </ul>
         <div class="form-group">
         <label>File cần tải</label>
         <input class="form-control" type = "file" name = "file" size = "50" required/>
         </div>
         <br />
         <input style="display: none;" name="id" ng-model="examID"/>
         <input class="btn btn-info btn-fill pull-right" type = "submit" value = "Upload" />
          <div class="clearfix"></div>
      </form>
      </div>
      </div>
      </div>
      
      <script>
      var myApp = angular.module('myApp', []) 
      		.config(function ($httpProvider) {
          $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
          $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
      		});
      myApp.controller('someCtrl', ['$scope', 'someService', function($scope, someService){    
    	    $scope.search = function(){
    	        someService.search($scope.keywords).then(function(response){
    	            $scope.response = response.data;
    	            $scope.examID = "";
    	        });
    	    };
			$scope.setExamID = function(id,title)
			{
				//$scope.selectedID = id;
				$scope.keywords = title;
				$scope.examID = id;
				delete $scope.response;
			};
    	}]);
      myApp.service('someService', ['$http', function($http){
    	    return {
    	        search: function(keywords){
    	            return $http.post('/XMLProject/findExambyName.jsp',"title="+keywords);
    	        }
    	    }
    	}]);
      </script>
      </jsp:attribute>
</t:layout>