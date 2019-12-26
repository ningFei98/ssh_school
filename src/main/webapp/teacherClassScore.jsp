<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成绩信息</title>
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
	<h3 style="text-align: center">学生成绩列表</h3>
	
	<div style="width:1000px;margin:0px auto;font-size: large;">
		<span>班级：${clazz.clazzName }</span>&nbsp;&nbsp;&nbsp;
		<span class="glyphicon glyphicon-calendar"></span>
		<span>${clazz.stage.stageName }第${clazz.stage.stageWeek }周</span>
	</div>
	<div style="width:1000px;margin:0px auto;text-align:right;">
		<a href="findClazzStu.action" class="btn btn-info">学员成绩录入</a>
	</div>
	<table class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>学生姓名</th>
				<th>学生成绩</th>
				<th>日期</th>
				<th>编辑</th>
			<tr>
		</thead>
		<tbody>
  		    <c:forEach items="${requestScope.scores}" var="sco" varStatus="i">
	  		    <tr>
	  		    	<th>${i.count }</th>
		  		    <th>${sco.student.studentName}</th>
		  		    <th>${sco.scoreNum}</th>
		  		    <th>${sco.scoreDate}</th>
		  		    <th><a href="findOneScore.action?score.scoreId=${sco.scoreId}" class="btn btn-info btn-xs">信息修改</a>
		  		    </th>
	  		    </tr>
  		    </c:forEach>
		</tbody>
	</table>
	<div style="width:1000px;margin:0px auto;">
		<form action="ScoresAdd.action" method="post" enctype="multipart/form-data"> 
			<input type="file" name="scoreExcel" label="成绩单" style="display: none" id="fileUploader" style="display:none;"/>
			
			<div style="width:1000px;margin:0px auto;text-align:right;">
				<div class="btn btn-primary" id="fileBtn" style="cursor:pointer;withd:30px;" onclick="document.getElementById('fileUploader').click();">
					<span>成绩单导入</span>
				</div>
				<input class="btn btn-primary" type="submit" value="文件提交" />
			</div>
		</form>
	</div>
	<script type="text/javascript" src="Refer/js/jquery-1.12.3.min.js"></script>
 	<script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
 	<!-- <script type="text/javascript">
 		$("#clazzselect").change(function(){
 			var clazzId = $("#clazzselect").val();
 			window.location.href="findScoreByClazz.action?clazz.clazzId="+clazzId;
 		})
 	</script> -->
</body>
</html>