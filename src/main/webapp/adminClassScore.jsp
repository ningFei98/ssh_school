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
	<h3 style="text-align: center">学生成绩列表</h3>
	
 	<div style="width:1000px;margin:0px auto;font-size: large;">
		<span>班级：${clazz.clazzName }</span>
		<span>阶段：${clazz.stage.stageName }  第${clazz.stage.stageWeek }周</span>
	</div>

	<table class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>学生姓名</th>
				<th>学生成绩</th>
				<th>日期</th>
				
			<tr>
		</thead>
		<tbody>
  		    <c:forEach items="${scores}" var="sco" varStatus="i">
	  		    <tr>
	  		    	<th>${i.count }</th>
		  		    <th>${sco.student.studentName}</th>
		  		    <th>${sco.scoreNum}</th>
		  		    <th>${sco.scoreDate}</th>
	  		    </tr>
  		    </c:forEach>
		</tbody>
	</table>
	<script type="text/javascript" src="Refer/js/jquery-1.12.3.min.js"></script>
 	<script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
 	
</body>
</html>