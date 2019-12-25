<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学员考勤信息</title>
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
	<h3 style="text-align: center">添加学员考勤</h3>

	<div style="width: 90%; margin: 20px auto; text-align: right;">
		<a
			href="findStudengtByClazzId.action?clazz.clazzId=${requestScope.sct.student.clazz.clazzId}"
			class="btn btn-info ">返回学员列表</a>
	</div>

	<form class="form-horizontal" role="form"
		style="width: 60%; margin: 10px auto;"
		action="overSaveCheckin.action?student.studentId=${requestScope.sct.student.studentId}"
		method="post">
		<div class="form-group">
			<label class="col-sm-3 control-label">姓名:</label> <label id="t1"
				class="col-sm-6 ">${requestScope.sct.student.studentName}</label>
		</div>
		<div class="form-group">
			<label for="checkInTypeId" class="col-sm-3 control-label">学员考勤:</label>
			<div class="col-sm-6">
				<select name="checkintype.checkInTypeId" class="form-control"
					id="checkInTypeId" required="required">
					<c:forEach items="${requestScope.sct.checkintypes}" var="types"
						varStatus="i">
						<option value="${types.checkInTypeId }">${types.checkInTypeName}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label for="inputEmail3" class="col-sm-3 control-label"></label>
			<div class="col-sm-6">
				<input type="submit" class="btn btn-info col-sm-12" value="提交考勤" />
			</div>
		</div>
	</form>

	<script type="text/javascript" src="Refer/js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
</body>
</html>