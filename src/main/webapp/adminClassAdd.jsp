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

</head>
<body>
  <form action="add.action" method="post" >
    <div id="out">
   
		<div class="col-sm-7">
		         班主任<br/>
			     <select id="overman" class="form-control" name="clazz.overman.overmanId">
			        <option>请选择</option>
			        <c:forEach items="${lo }" var="g" >
			            <option value="${g.overmanId }">${g.overmanName }</option>
			        </c:forEach>
			     </select>
	    </div>
	    <div class="col-sm-7">
		      任课教师<br/>
		        <select id="teacher" class="form-control" name="clazz.teacher.teacherId">
		            <option>请选择</option>
		            <c:forEach items="${te }" var="m">
		               <option value="${m.teacherId }">${m.teacherName }</option>
		            </c:forEach>
		        </select>
		</div>
		<div class="col-sm-7">
		    班级名称<br/>
		    <input type="text"  class="form-control" name="clazz.clazzName">
		</div>
		<div class="col-sm-7">    
		    班级类型<br/>
		        <select id="type" class="form-control" name="clazz.clazzType"> 
		           <option>请选择</option>
		           <option>测试开发</option>
		           <option>java开发</option>
		        </select>
		</div>
		<div class="col-sm-7">    
		        <input type="submit" class="btn btn-info col-sm-12 col-xs-12" value="保存"/>
		</div>	
	
	</div>
	</form>
</body>
</html>