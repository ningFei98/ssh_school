<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学员信息修改页面</title>
<link href="Refer/css/bootstrap.min.css" type="text/css"
	rel="stylesheet" />
<style type="text/css">
#t1 {
	padding-top: 7px;
	margin-bottom: 0;
	text-align: left;
}
</style>
</head>
<body>
	<h3 style="text-align: center">学员信息修改</h3>

	<div style="width: 90%; margin: 20px auto; text-align: right;">
		<a
			href="findStudengtByClazzId.action?clazz.clazzId=${requestScope.student.clazz.clazzId}"
			class="btn btn-info ">返回学员列表</a>
	</div>

	<form class="form-horizontal" role="form"
		style="width: 60%; margin: 10px auto;"
		action="overUpdStudent.action?student.studentId=${requestScope.student.studentId}&clazz.clazzId=${requestScope.student.clazz.clazzId}"
		method="post">
		<div class="form-group">
			<label for="studentName" class="col-sm-3 control-label">学生姓名:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="studentName"
					 name="student.studentName"
					value="${requestScope.student.studentName}">
			</div>
		</div>

		<div class="form-group">
			<label for="studentSex" class="col-sm-3 control-label">学生性别:</label>
			<div class="col-sm-6">
				<select name="student.studentSex" class="form-control"
					id="studentSex" required="required">
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="studentPhone" class="col-sm-3 control-label">学生电话:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" required="required"
					id="studentPhone"  name="student.studentPhone"
					value="${requestScope.student.studentPhone}">
			</div>
		</div>
		<div class="form-group">
			<label for="studentAddress" class="col-sm-3 control-label">学生地址:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" required="required"
					id="studentAddress" 
					name="student.studentAddress"
					value="${requestScope.student.studentAddress}">
			</div>
		</div>
		<div class="form-group">
			<label for="studentEdu" class="col-sm-3 control-label">学生学历:</label>
			<div class="col-sm-6">
				<select name="student.studentEdu" class="form-control"
					id="studentEdu" required="required">
					<option value="本科">本科</option>
					<option value="专科">专科</option>
					<option value="研究生">研究生</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="studentCollege" class="col-sm-3 control-label">毕业学校:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" required="required"
					id="studentCollege" 
					name="student.studentCollege"
					value="${requestScope.student.studentCollege}">
			</div>
		</div>
		<div class="form-group">
			<label for="studentProfessional" class="col-sm-3 control-label">学生专业:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" required="required"
					id="studentProfessional" 
					name="student.studentProfessional"
					value="${requestScope.student.studentProfessional}">
			</div>
		</div>

		<div class="form-group">
			<label for="inputEmail3" class="col-sm-3 control-label"></label>
			<div class="col-sm-6">
				<input type="submit" class="btn btn-info col-sm-12" value="提交修改" />
			</div>
		</div>
	</form>

	<script type="text/javascript" src="Refer/js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
</body>
</html>