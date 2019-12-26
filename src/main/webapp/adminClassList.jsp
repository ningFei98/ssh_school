<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <%@  taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link href="Refer/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>

</head>
<body>
    <h3 style="text-align: center">班级教学信息</h3>
	<table class="table table-bordered table-striped table-hover table-condensed" style="width:90%;margin: 20px auto;">
	     <thead>
	        <tr>
	           <th>班级编号</th>
	           <th>班主任</th>
	           <th>任课教师</th>
	           <th>班级阶段</th>
	           <th>班级名称</th>
	           <th>班级类型</th>
	           <th>操作</th>
	        </tr>
	     </thead>
	     <tbody>
	         <c:forEach items="${li }" var="g" varStatus="i">
                 <tr>
                     <td>${i.count}</td>
                     <td>${g.overman.overmanName }</td>
                      <td>${g.teacher.teacherName }</td>
                     <td>${g.stage.stageName }</td>
                    
                     <td>${g.clazzName }</td>
                     <td>${g.clazzType }</td>
                     <th>
                         <input type="button" value="班级成绩" onclick="location.href='findScore.action?clazz.clazzId=${g.clazzId}&clazz.clazzName=${g.clazzName }&clazz.stage.stageName=${g.stage.stageName }&clazz.stage.stageWeek=${g.stage.stageWeek }'" class="btn btn-info btn-xs"/>
                         <input type="button" value="班级考勤" onclick="location.href='findCheck.action?clazz.clazzId=${g.clazzId}&clazz.clazzName=${g.clazzName }&clazz.stage.stageName=${g.stage.stageName }&clazz.stage.stageWeek=${g.stage.stageWeek }'" class="btn btn-info btn-xs"/>
                     </th>
                 </tr>
	         </c:forEach>
	     </tbody>
	</table>
	
	<script type="text/javascript" src="Refer/js/jquery-1.12.3.min.js"></script>
 	<script type="text/javascript" src="Refer/js/bootstrap.min.js"></script>
</body>
</html>