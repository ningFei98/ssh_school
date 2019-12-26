<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
 <head>
     <meta charset="utf-8" />
     <title></title>
     
     
 </head>
 <body>
     <div id="main" style="width:600px;height:600px"></div>
     
     <script type="text/javascript" src="Refer/js/jquery-1.12.3.min.js"></script>
     <script src="Refer/js/echarts.min.js"></script>
     <script type="text/javascript">
         
         $.ajax({
             url: "chart.action",
             type: "post",
             dataType:"json",
             success: function (data) {
                   
                   //创建并初始化chart实例
                   var myEchart = echarts.init(document.querySelector('#main'));
                   
                   var option = {
                           //设置
                           title:{
                               text:'学校各类班级统计分布',
                               x:'center'
                           },
                           tooltip:{
                               trigger:'item',
                               formatter: "{a} <br/>{b} : {c} ({d}%)"
                           },
                           //对图例组件的不同系列进行标记说明
                           legend:{
                               orient:'vertical',  //设置图例列表的布局朝向
                               left:'left',
                               data:['测试开发','Java开发']
                           },
                           //系列列表
                           series:[
                               //系列1
                               {
                                   name:'学生成绩分布',
                                   type:'pie',    //数据统计图的类型
                                   //放置要展示的数据
                                   data:[
                                       {value:data.countC,name:'测试开发'},
                                       {value:data.countJ,name:'Java开发'},
              
                                   ]
                               }
                           ]
                       }
                   myEchart.setOption(option);
             }
         })
 
     </script>
 </body>
 </html>