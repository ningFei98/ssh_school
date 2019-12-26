<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考勤信息</title>
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
	<h3 style="text-align: center">学生考勤列表</h3>
	
	<div style="width:1000px;margin:0px auto;font-size: large;">
		<span>班级：${clazz.clazzName }</span>&nbsp;&nbsp;&nbsp;
		<span class="glyphicon glyphicon-calendar"></span>
		<span>${clazz.stage.stageName }第${clazz.stage.stageWeek }周</span>
		&nbsp;&nbsp;&nbsp;
		<span>考勤日期：${checkinList[0].checkInDate}</span>
	</div>
	<table class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th rowspan="2">序号\考勤</th>
				<th rowspan="2">学生姓名</th>
				<th>考勤情况</th>	
			<tr>
		</thead>
		<tbody>
			<c:forEach items="${checkinList}" varStatus="i" var="ch">
				<tr>
					<th>${i.count }</th>
					<th>${ch.student.studentName }</th>
					<th>${ch.checkintype.checkInTypeName }</th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="width:1000px;margin:0px auto;">
		
	</div>
	<script type="text/javascript" src="Refer/js/jquery-1.12.3.min.js"></script>
 
</body>
</html>