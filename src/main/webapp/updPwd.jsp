<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="Refer/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
	<form class="form-horizontal" role="form" style="width: 550px;margin: 10px auto;" action="#" method="post">
		<div class="form-group">
			<label class="col-md-5 col-sm-5 control-label">原密码：</label>
			<div class="col-md-4 ">
				<input type="password" class="form-control"  required="required" name="userOldPwd" id="mima">
			</div>
			<div class="clo-md-3" id="showPwd" style="color: red"></div>
		
			
		</div>
		<div class="form-group">
			<label class="col-md-5 col-sm-5 control-label">请输入新密码：</label>
			<div class="col-md-4 " >
			
			   <input type="text" class="form-control" required="required" name="userNewPwd">			   
			   <label id="showd"></label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-5 col-sm-5 control-label">请确认密码：</label>
			<div class="col-md-4 " >
			
			   <input type="text" class="form-control" required="required" name="userNewPwd1">
			   
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-5 col-sm-5"></label>
			<div class="col-md-4" >
				<input type="submit" class="btn btn-info btn-block" value="确认">
				
			</div>
			
		</div>
		
	</form>
<script type="text/javascript"  src="Refer/js/jquery-1.12.3.min.js"></script>

<script type="text/javascript"  src="Refer/js/bootstrap.min.js"></script>	
<script type="text/javascript">	
var tag=false;
	 $("[name=userOldPwd]").change(function(){ 
		/* $("#mima").change(function(){ */
		
		$.ajax({
			cache:false,
			url:"change.action",
			type:"post",
			data:{"oldPwd":$("[name=userOldPwd]").val()},
			async:false,
			error:function(data){
				alert("返回错误");
			},
			success:function(data){
				
				if(data=="true"){
		    		
		    		tag=true;
		    		$("#showPwd").html("");
		    	}
		    	else{
		    		tag=false;
		    		$("#showPwd").html("原密码输入不正确");
		    	}
			}
			
		});
		return false;
	});
	
	 $(":submit").click(function(){
		if(tag==false){
			
			alert("原密码不正确");
			return false;
		}
		if($("[name=userNewPwd]").val()!=$("[name=userNewPwd1]").val()){
			
			alert("确认密码和新密码不一致");
			return false;
		}
		if($("[name=userNewPwd]").val()==$("[name=userNewPwd1]").val()){
			$.ajax({
				
				cache:false,
				url:"upd.action",
				type:"post",
				data:{"newPwd":$("[name=userNewPwd]").val()},
				async:false,
				success:function(data){
					alert("修改密码成功");
					top.location.href="userLogin.jsp";
					}
				});
			
			}
		return false;
	}) 
	
	
	  $("[name=userNewPwd]").keyup(function(){
		 checkuname();
	 })
	 	function checkuname(){
		 $("#showd").empty();
		 var namel=$("[name=userNewPwd]").val().length;
		 
		 var b=$("<font></font>");
		 b.attr("color","red");
		 b.text("密码长度需要在3-12之间");
		 var flag=false;
		 if(namel>=3&&namel<12){
			 flag=true;
			 b.attr("color","green");
			 b.text("该密码可以使用！");
		 }		 
		 $("#showd").append(b);
		 return flag;
		
	 } 
	
</script>

</body>
</html>