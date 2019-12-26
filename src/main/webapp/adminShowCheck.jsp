<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="snf" uri="http://java.sun.com/jsp/jstl/core"%>
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
     <div style="width:1000px;margin:0px auto;font-size: large;">
		<span>班级：${clazz.clazzName }</span>
		<span>阶段：${clazz.stage.stageName }  第${clazz.stage.stageWeek }周</span>
	</div>
	<table class="table table-bordered table-striped table-hover table-condensed" style="width:90%;margin: 20px auto;">
		<thead>
			<tr>
				<th>序号</th>
				<th>学生姓名</th>
				<th>考勤类型</th>
			</tr>
		</thead>
		<tbody>
		  	<snf:forEach items="${lc}" var="c" varStatus="i">
			<tr>
				<td>${i.count}</td>
				<td>${c.studentName}</td>
				<td>
				正常：${c.zhengchang}次&nbsp;
				迟到：${c.chidao }次&nbsp;
				旷课：${c.kuangke }次&nbsp;
				请假：${c.qingjia }次&nbsp;

				</td>
			</tr>
			</snf:forEach>
		</tbody>
	</table>
  <script type="text/javascript" src="Refer/js/jquery-3.4.1.min.js"></script>
  <script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
</body>
</html>