<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成绩信息</title>
<link href="Refer/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
	<style type="text/css">
		.table{
			width: 1000px;
			margin: 20px auto;
		}
		th,th{
			text-align: center;
		}
		
	</style>
</head>
<body>
	<h3 style="text-align: center">学生信息</h3>
	
	<div style="width:1000px;margin:0px auto;font-size: large;">
		<span>班级：${clazz.clazzName }</span>&nbsp;&nbsp;&nbsp;
		<span class="glyphicon glyphicon-calendar"></span>
		<span>阶段：${clazz.stage.stageName }第${clazz.stage.stageWeek }周</span>
	</div>
	<table class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>学生姓名</th>
				<th>学生性别</th>
				<th>手机号</th>
				<th>学历</th>
				
			<tr>
		</thead>
		<tbody>
  		    <c:forEach items="${requestScope.stuList}" var="stu" varStatus="i">
	  		    <tr>
	  		    	<th>${i.count }</th>
		  		    <th>${stu.studentName}</th>
		  		    <th>${stu.studentSex}</th>
		  		    <th>${stu.studentPhone}</th>
		  		    <th>${stu.studentEdu}</th>
	  		    </tr>
  		    </c:forEach>
		</tbody>
	</table>
	
	<script type="text/javascript" src="Refer/js/jquery-1.12.3.min.js"></script>
 	<script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
 	<!-- <script type="text/javascript">
 		$("#clazzselect").change(function(){
 			var clazzId = $("#clazzselect").val();
 			window.location.href="findScoreByClazz.action?clazz.clazzId="+clazzId;
 		})
 	</script> -->
</body>
</html>