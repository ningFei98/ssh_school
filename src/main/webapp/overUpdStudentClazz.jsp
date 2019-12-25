<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学员转班页面</title>
<link href="Refer/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
#t1 {
    padding-top: 7px;
    margin-bottom: 0;
    text-align: left;
}

</style>
</head>
<body>
<h3 style="text-align: center">学员转班操作</h3>

	<div style="width:90%;margin:20px auto;text-align: right;">
		<a href="findStudengtByClazzId.action?clazz.clazzId=${requestScope.student.clazz.clazzId}" class="btn btn-info " >返回学员列表</a>
	</div>

<form class="form-horizontal" role="form"
			style="width: 60%; margin: 10px auto;" action="overUpdStudentClazz.action?student.studentId=${requestScope.student.studentId}"
			method="post" >
			<div class="form-group">
				<label  class="col-sm-3 control-label">姓名:</label>
				<label  id="t1" class="col-sm-6 ">${requestScope.student.studentName}</label>
			</div>
			<div class="form-group">
				<label  class="col-sm-3 control-label">原来班级:</label>
				<label  id="t1" class="col-sm-6 ">${requestScope.student.clazz.clazzName}${requestScope.student.clazz.clazzType}</label>
			</div>
			<div class="form-group">
				<label for="clazzId" class="col-sm-3 control-label">转班班级:</label>
				<div class="col-sm-6">
					<select name="clazz.clazzId" class="form-control" id="clazzId" required="required">
							<c:forEach items="${requestScope.clazzs}" var="c" varStatus="i">
								<option  value="${c.clazzId }">${c.clazzName}${c.clazzType}</option>
							</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-3 control-label"></label>
				<div class="col-sm-6" >
					<input type="submit" class="btn btn-info col-sm-12" value="提交转班" />
				</div>
			</div>
		</form>

 <script type="text/javascript" src="Refer/js/jquery-3.4.1.min.js"></script>
 <script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
</body>
</html>