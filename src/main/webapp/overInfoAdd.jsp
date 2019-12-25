<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学员信息</title>
<link href="Refer/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<h3 style="text-align: center">添加学员信息</h3>

<div style="width:90%;margin:20px auto;text-align: right;">
		<a href="findClazz.action" class="btn btn-info " >返回班级列表</a>
	</div>

<form class="form-horizontal" role="form"
	style="width: 60%; margin: 10px auto;" action="overSaveStudent.action"
	method="post" >
	<div class="form-group">
		<label for="studentName" class="col-sm-3 control-label">学生姓名:</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" required="required" id="studentName"
				placeholder="请输入学生姓名" name="student.studentName">
		</div>
	</div>
	<div class="form-group">
		<label for="clazzId" class="col-sm-3 control-label">学员班级:</label>
		<div class="col-sm-6">
			<select name="clazz.clazzId" class="form-control" id="clazzId" required="required">
					<c:forEach items="${requestScope.clazzs}" var="c" varStatus="i">
						<option  value="${c.clazzId }">${c.clazzName}${c.stage.stageName}</option>
					</c:forEach>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label for="studentSex" class="col-sm-3 control-label">学生性别:</label>
		<div class="col-sm-6">
			<select name="student.studentSex" class="form-control" id="studentSex" required="required">
				<option  value="男">男</option>
				<option  value="女">女</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label for="studentPhone" class="col-sm-3 control-label">学生电话:</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" required="required" id="studentPhone"
				placeholder="请输入学生电话" name="student.studentPhone">
		</div>
	</div>
	<div class="form-group">
		<label for="studentAddress" class="col-sm-3 control-label">学生地址:</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" required="required" id="studentAddress"
				placeholder="请输入学生地址" name="student.studentAddress">
		</div>
	</div>
	<div class="form-group">
		<label for="studentEdu" class="col-sm-3 control-label">学生学历:</label>
		<div class="col-sm-6">
			<select name="student.studentEdu" class="form-control" id="studentEdu" required="required">
				<option  value="本科">本科</option>
				<option  value="专科">专科</option>
				<option  value="研究生">研究生</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label for="studentCollege" class="col-sm-3 control-label">毕业学校:</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" required="required" id="studentCollege"
				placeholder="请输入毕业学校" name="student.studentCollege">
		</div>
	</div>
	<div class="form-group">
		<label for="studentProfessional" class="col-sm-3 control-label">学生专业:</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" required="required" id="studentProfessional"
				placeholder="请输入学生专业" name="student.studentProfessional">
		</div>
	</div>
	
	
	<div class="form-group">
		<label for="inputEmail3" class="col-sm-3 control-label"></label>
		<div class="col-sm-6" >
			<input type="submit" class="btn btn-info col-sm-12" value="保存信息" />
		</div>
	</div>
</form>

 <script type="text/javascript" src="Refer/js/jquery-3.4.1.min.js"></script>
 <script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
</body>
</html>