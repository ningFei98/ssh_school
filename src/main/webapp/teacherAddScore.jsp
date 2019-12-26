<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加学院成绩</title>
<link href="Refer/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<style type="text/css">

			#right{
				height: 600px;
				font-size: 20px;
			}
			
		</style>
</head>
<body>
	<div id="outer" class="container-fluid">
			
		<div class="row">
			<div id="right" class="col-lg-12">
				<form class="form-horizontal" role="form" style="width: 550px;margin: 50px auto;" action="saveScore.action" method="post">
					  
				   <div class="form-group">
				   		<label for="name"  class="col-sm-3 control-label">学生姓名:</label>
				    	<div class="col-sm-9">
					    	<select class="form-control" name="student.studentId">
					      		<c:forEach items="${students}" var="stu">
					      			<option value="${stu.studentId }">${stu.studentName }</option>
					      		</c:forEach>
					    	</select> 
					    </div>
				    </div>	
				   
				    <div class="form-group">
					    <label for="goodsPrice" class="col-sm-3 control-label">成绩:</label>
					    <div class="col-sm-9">
					    	<input type="text" class="form-control" id="goodsPrice" placeholder="" name="score.scoreNum">
					    </div>
				   </div>
 					  
				   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-3 control-label"></label>
					    <div class="col-sm-9">
					       <input type="submit" class="btn btn-info col-sm-12" value="提交" />
					    </div>
				   </div>
 					   
 				</form>
			</div>
		</div>
			
	</div>
</body>
</html>