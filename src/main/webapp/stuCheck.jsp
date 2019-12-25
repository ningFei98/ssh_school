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
			
			<th>考勤结果</th>
			<th>考勤人</th>
			<th>考勤结果</th>
			<th>考勤日期</th>
			
		</thead>
			<c:forEach items="${loginUser.student.checkins}" var="h">
			<tr>
												
			
				<th>${h.checkInId}</th>
				<th>${h.student.studentName}</th>
				<th>${h.checkintype.checkInTypeName}</th>	
				<th>${h.checkInDate}</th>	
			</tr>
			</c:forEach>

		<tbody>
			
		</tbody>
	</table>
	<script type="text/javascript" scr="js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>