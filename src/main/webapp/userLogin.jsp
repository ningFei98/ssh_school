<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录</title>
</head>
<link rel="stylesheet" href="Refer/css/bootstrap.min.css" />
<style type="text/css">
/* 	#login {
	height: 300PX;
	width:500px;
	background-color:teal; 
	text-align: center;
	font-size: 30px;
} */
</style>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">首页</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="Role_findAll.action"><span class="glyphicon glyphicon-user"></span>注册</a></li>
				<li><a href="userLogin.jsp"><span class="glyphicon glyphicon-log-in"></span>登录</a></li>
			</ul>
		</div>
	</nav>
	<h3 style="text-align: center;">用户登录</h3>
		<form class="form-horizontal" role="form"
			style="width: 40%; margin: 10px auto;" action="login.action"
			method="post" >
			<div class="form-group">
				<label for="userName" class="col-sm-3 control-label">用户名:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" required="required" id="userName"
						placeholder="请输入用户名" name="user.userName">
				</div>
			</div>
			<div class="form-group">
				<label for="userPwd" class="col-sm-3 control-label">密码:</label>
				<div class="col-sm-6">
					<input type="password" class="form-control" required="required" id="userPwd"
						placeholder="请输入密码" name="user.userPwd">
				</div>
			</div>
			<div class="form-group" >
				<input style="width:60px;margin-left:200px;" type="button" class="btn btn-info " onclick="location.href='Role_findAll.action'" value="注册" />
				<input style="width:60px" type="submit" class="btn btn-info " value="登录" />
				<input style="width:60px" type="reset" class="btn btn-info " value="重置" />
			</div>
		</form>
		
</body>
</html>