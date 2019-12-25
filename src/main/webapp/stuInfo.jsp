<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生信息</title>
<link href="Refer/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
	<table class="table table-bordered table-striped table-hover table-condensed" style="width:700px;margin: 20px auto">
		<thead>
			
			<th>学生姓名</th>
			<th>学生编号</th>
			<th>学生性别</th>
			<th>联系电话</th>
			<th>居住地</th>
			<th>学历</th>
			<th>毕业学校</th>
			<th>专业</th>
			<th>毕业时间</th>
		</thead>

			<tr>
				
				<th>${loginUser.student.studentName}</th>								
				<th>${loginUser.student.sutdentCode}</th>
				<th>${loginUser.student.studentSex}</th>
				<th>${loginUser.student.studentPhone}</th>
				<th>${loginUser.student.studentAddress}</th>
				<th>${loginUser.student.studentEdu}</th>
				<th>${loginUser.student.studentCollege}</th>
				<th>${loginUser.student.studentProfessional}</th>
				<th>${loginUser.student.studentGraduateTime}</th>								
			</tr>		
		<tbody>
			
		</tbody>
	</table>
	<script type="text/javascript" scr="js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>