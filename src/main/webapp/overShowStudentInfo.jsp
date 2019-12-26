<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级学员列表</title>
<link href="Refer/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
.table th, .table td { 
text-align: center;
vertical-align: middle!important;
}
</style>
</head>
<body>

	<h3 style="text-align: center">${requestScope.clazz.clazzName}学员信息</h3>
<div style="width:90%;margin:20px auto;text-align: right;">
		<a href="overBatchAddStudentView.action" class="btn btn-info " >批量添加学员</a>
		<a href="overAddStudent.action" class="btn btn-info " >单个添加加学员</a>
		<a href="findClazz.action" class="btn btn-info " >返回班级列表</a>
	</div>


	<table class="table table-bordered table-striped table-hover table-condensed" style="width:90%;margin: 20px auto;">
		<thead>
			<tr>
				<th>序号</th>
				<th>学生姓名</th>
				<th>学号</th>
				<th>性别</th>
				<th>手机号</th>
				<th>学历</th>
				<th>学校</th>
				<th>专业</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		  	<c:forEach items="${requestScope.clazz.students}" var="s" varStatus="i">
			<tr>
				<td>${i.count}</td>
				<td>${s.studentName}</td>
				<td>${s.sutdentCode}</td>
				<td>${s.studentSex}</td>
				<td>${s.studentPhone}</td>
				<td>${s.studentEdu}</td>
				<td>${s.studentCollege}</td>
				<td>${s.studentProfessional}</td>
				<td>
					<a href="findChecksByStudentId.action?student.studentId=${s.studentId}&clazz.clazzId=${s.clazz.clazzId}" class="btn btn-info btn-sm">查看考勤</a>
					<a href="findAllCheckintype.action?student.studentId=${s.studentId}" class="btn btn-info btn-sm">添加考勤</a>
					<a href="overUpdStudentView.action?student.studentId=${s.studentId}" class="btn btn-info btn-sm">修改信息</a>
					<a href="overGetStudentByStudentId.action?student.studentId=${s.studentId}" class="btn btn-info btn-sm">转班</a>
					<a href="overDeleteStudentByStudentId.action?student.studentId=${s.studentId}" class="btn btn-danger btn-sm">删除</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="width:90%;margin:20px auto;text-align: right;">
		<a href="studentExcelWrite.action?clazz.clazzId=${clazz.clazzId }" class="btn btn-info " >导出学生信息表</a>
	</div>
  <script type="text/javascript" src="Refer/js/jquery-3.4.1.min.js"></script>
  <script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
</body>
</html>