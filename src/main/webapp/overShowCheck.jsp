<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学员考勤信息</title>
<link href="Refer/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
.table th, .table td { 
text-align: center;
vertical-align: middle!important;
}
</style>
</head>
<body>

	<h3 style="text-align: center">学员考勤信息</h3>
	<div style="width:90%;margin:20px auto;text-align: right;">
		<a href="findStudengtByClazzId.action?clazz.clazzId=${requestScope.clazz.clazzId}" class="btn btn-info " >返回学员列表</a>
	</div>

	<table class="table table-bordered table-striped table-hover table-condensed" style="width:90%;margin: 20px auto;">
		<thead>
			<tr>
				<th>序号</th>
				<th>学生姓名</th>
				<th>考勤类型</th>
				<th>考勤时间</th>
			</tr>
		</thead>
		<tbody>
		  	<c:forEach items="${requestScope.checks}" var="c" varStatus="i">
			<tr>
				<td>${i.count}</td>
				<td>${c.student.studentName}</td>
				<td>${c.checkintype.checkInTypeName}</td>
				<td>${c.checkInDate}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
  <script type="text/javascript" src="Refer/js/jquery-3.4.1.min.js"></script>
  <script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
</body>
</html>