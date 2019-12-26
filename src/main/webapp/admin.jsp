<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>教学管理系统</title>
<link href="Refer/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
			#top{
				height: 100px;
				background-color: whitesmoke;
				text-align: center;
				padding-top: 10px;
			}
			#left{
				height: 600px;
				
			}
			#right{
				height: 600px;
			}
			#bottom{
				height: 40px;
				text-align: center;
				line-height: 40px;
				background-color:5bc0dc;
			}
			
		</style>
</head>
<body>
	<div id="outer" class="container-fluid">
		
		<div class="row">
			<div id="top" class="col-lg-12">
					<h1>教学管理系统</h1>
					<h5 style="text-align:right">
						当前用户：${sessionScope.loginUser.userName}
						<a href="userExit.action">安全退出</a>
					</h5>
			</div>
		</div>
			
			<div class="row">
				<div id="left" class="col-lg-3">
					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						
					  
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="headingThree">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
					          	班级管理
					        </a>
					      </h4>
					    </div>
					    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
					      <div class="panel-body">
					        <ul>
					        	
					        	<li><a href="admin.action" target="my">查看班级</a></li>
					        	<li><a href="adminClassAdd.action" target="my">新开班级</a></li>
					        </ul>
					      </div>
					    </div>
					  </div>
					
				</div>	
				</div>
				
				<div id="right" class="col-lg-9">
						<iframe name="my" style="width:100%;height:100%" src="adminClassInfor.jsp"></iframe>
					
				</div>
			
			
			<div class="row">
				<div id="bottom" class="col-lg-12">
					<p>&copy;版权信息 当前在线人数：${applicationScope.online}</p>
				</div>
			</div>
			
		</div>
	</div>
	<script type="text/javascript" src="Refer/js/jquery-1.12.3.min.js"></script>
 	<script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
</body>
</html>