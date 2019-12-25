<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生管理系统</title>
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
				background-color: cornflowerblue;
			}
			
		</style>
</head>
<body>
	<div id="outer" class="container-fluid">
		
		<div class="row">
			<div id="top" class="col-lg-12">
					<h1>学生信息管理系统</h1>
					<h5 style="text-align:right">
						当前用户：${sessionScope.uName}
						<a href="userExit.do">安全退出</a>
					</h5>
			</div>
		</div>
			
			<div class="row">
				<div id="left" class="col-lg-3 col-md-3 col-sm-3">
					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingOne">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
					          			学生管理
					        		</a>
					      		</h4>
					    	</div>
					    	<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					      		<div class="panel-body">
					        		<ul>
							        	<li><a href="stuInfo.jsp" target="ifs">学生信息</a></li>
							        	<li><a href="stuCheck.jsp" target="ifs">学生考勤</a></li>
							        	<li><a href="stuScore.jsp" target="ifs">学生成绩</a></li>
							        	<li><a href="updPwd.jsp" target="ifs">修改密码</a></li>
					        		</ul>
					      		</div>
					   		</div>
					  </div>
					  
					  
					
				</div>	
				</div>
				
				<div id="right" class="col-lg-9 col-md-9 col-sm-9">
						<iframe name="ifs" style="width:100%;height:100%" ></iframe>
					
				</div>
			
			
			<div class="row">
				<div id="bottom" class="col-lg-12 col-md-12 col-sm-12">
					<p>&copy;版权信息 当前在线人数：${applicationScope.online}</p>
				</div>
			</div>
			
		</div>
	</div>
	<script type="text/javascript" src="Refer/js/jquery-1.12.3.min.js"></script>
 	<script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
</body>
</html>