<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <%@  taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link href="Refer/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
   #out{
      
	     		padding-left:130px ;
	     		padding-top:60px ;
	     		
	     		font-family: "微软雅黑";
   }
</style>
<script type="text/javascript" src="Refer/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
<!-- <script type="text/javascript">
   $(function(){
	   $.ajax({
		  cache:false,
		  type:"post",
		  url:"adminAdd.action",
		  dataType:"json",
		  async:"true",
		  error:function(data){
			  alert("false");
		  },
	      success:function(data){
	    	  alert(data);
	    	  
	    	  
	      }
	   });
   });

</script> -->
</head>
<body>
    <div id="out">
   
		<div class="col-sm-7">
		         班主任<br/>
			     <select id="overman" class="form-control">
			        <option>请选择</option>
			     </select>
	    </div>
	    <div class="col-sm-7">
		      任课教师<br/>
		        <select id="teacher" class="form-control">
		            <option>请选择</option>
		        </select>
		</div>
		<div class="col-sm-7">
		    班级期数<br/>
		    <input type="text"  class="form-control" >
		</div>
		<div class="col-sm-7">    
		    班级类型<br/>
		        <select id="type" class="form-control"> 
		           <option>请选择</option>
		           <option>测试开发</option>
		           <option>java开发</option>
		        </select>
		</div>
		<div class="col-sm-7">    
		        <input type="submit" class="btn btn-info col-sm-12 col-xs-12" value="保存"/>
		</div>
		
		</form>
	
	</div>
	
</body>
</html>