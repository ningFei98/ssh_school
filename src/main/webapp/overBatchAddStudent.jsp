<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量添加学员信息</title>
<link href="Refer/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<h3 style="text-align: center">批量添加学员信息</h3>

<div style="width:90%;margin:20px auto;text-align: right;">
		<a href="findClazz.action" class="btn btn-info " >返回班级列表</a>
	</div>

<form class="form-horizontal" role="form"
	style="width: 60%; margin: 10px auto;" action="overBatchAddStudentSave.action"
	method="post"  enctype="multipart/form-data">
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
		<label for="userUploadFile" class="col-sm-3 control-label">导入Excel学员表:</label>
		<div class="col-sm-6">
			<input type="file"  required="required" id="userUploadFile"
				 name="userUploadFile">
		</div>
	</div>
	
	
	<div class="form-group">
		<label for="inputEmail3" class="col-sm-3 control-label"></label>
		<div class="col-sm-6" >
			<input type="submit" class="btn btn-info col-sm-12" value="确认添加" />
		</div>
	</div>
</form>

 <script type="text/javascript" src="Refer/js/jquery-3.4.1.min.js"></script>
 <script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
</body>
</html>