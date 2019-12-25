<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所属班级</title>
<link href="Refer/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
.table th, .table td { 
text-align: center;
vertical-align: middle!important;
}
</style>
</head>
<body>

	<h3 style="text-align: center">班级列表</h3>
	<div style="width:90%;margin:20px auto;text-align: right;">
		<a href="overBatchAddStudentView.action" class="btn btn-info " >批量添加学员</a>
		<a href="overAddStudent.action" class="btn btn-info " >单个添加学员</a>
	</div>

	<table class="table table-bordered table-striped table-hover table-condensed" style="width:90%;margin: 20px auto;">
		<thead>
			<tr>
				<th>序号</th>
				<th>班主任</th>
				<th>任课老师</th>
				<th>班级阶段</th>
				<th>班级名</th>
				<th>班级类型</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		  	<c:forEach items="${requestScope.clazzs}" var="c" varStatus="i">
			<tr>
				<td>${i.count}</td>
				<td>${c.overman.overmanName}</td>
				<td>${c.teacher.teacherName}</td>
				<td>${c.stage.stageName}第${c.stage.stageWeek}周</td>
				<td>${c.clazzName}</td>
				<td>${c.clazzType}</td>
				<td>
					<a href="findStudengtByClazzId.action?clazz.clazzId=${c.clazzId}" class="btn btn-info ">查看班级学员信息</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
  <script type="text/javascript" src="Refer/js/jquery-3.4.1.min.js"></script>
  <script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
</body>
</html>