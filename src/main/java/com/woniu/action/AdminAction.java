package com.woniu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;
import com.woniu.pojo.Chart;
import com.woniu.pojo.CheckOrderBy;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Overman;
import com.woniu.pojo.Score;
import com.woniu.pojo.Stage;
import com.woniu.pojo.Teacher;
import com.woniu.service.AdminService;

    @Controller
    public class AdminAction {
	    private Clazz clazz;
		public Clazz getClazz() {
			return clazz;
		}
		public void setClazz(Clazz clazz) {
			this.clazz = clazz;
		}
		
		//-------------------------------接收所有班级信息
		private List<Clazz> li =new ArrayList<Clazz>();
		public List<Clazz> getLi() {
			return li;
		}
		public void setLi(List<Clazz> li) {
			this.li = li;
		}
		
		//---------------------------------接收所有老师信息
		private List<Teacher> te=new ArrayList<Teacher>();
		public List<Teacher> getTe() {
			return te;
		}
		public void setTe(List<Teacher> te) {
			this.te = te;
		}
		
		//---------------------------------接收所有班主任信息
		private List<Overman> lo=new ArrayList<Overman>();
		public List<Overman> getLo() {
			return lo;
		}
		public void setLo(List<Overman> lo) {
			this.lo = lo;
		}
		
		//---------------------------------接收所有考勤信息
		private List<CheckOrderBy> lc;
		public List<CheckOrderBy> getLc() {
			return lc;
		}
		public void setLc(List<CheckOrderBy> lc) {
			this.lc = lc;
		}
		
		//---------------------------------接收所有成绩信息
		private List<Score> scores = new ArrayList<Score>();
		public List<Score> getScores() {
			return scores;
		}
		public void setScores(List<Score> scores) {
			this.scores = scores;
		}

		//---------------------------------封装阶段信息
		private Stage stage;	
		public Stage getStage() {
			return stage;
		}
		public void setStage(Stage stage) {
			this.stage = stage;
		}

		//查询所有的班级信息--------clazz
		@Autowired
		AdminService  adminService;
		public String findAll() {
			
			li=adminService.findAll();
			return "success";
		}
		
		
        //查询新增班级时的所有信息
		public String find() {
			System.out.println("------------Action---find");
			//查询班主任老师信息
			lo=adminService.findOverman();
			System.out.println("==============================");
			System.out.println(lo);
			//查询老师信息
			te=adminService.findTeacher();
			System.out.println("==============================");
			System.out.println(te);
			return "success";
		}
		
		
		

		
		//保存新增班级
		public String save() {
			System.out.println(clazz);
			adminService.save(clazz);
			return "success";
			
		}
		
		//查询班级分数
		public String findScore() {
			System.out.println(clazz);
			int a=clazz.getClazzId();
			//获取班级分数
			scores=adminService.findScoreByClazz(a);
			
			return "success";
			
		}
		
		//查询班级考勤
		public String findCheck() {
			System.out.println("++++++++++++++++++++++++++++findCHECK");
			System.out.println(clazz);
			int a=clazz.getClazzId();
			lc=adminService.findCheck(a);
			System.out.println(lc);
			return "success";
		}
		
		//安全退出
		public String userDelete() {
			System.out.println("安全退出");
			//删除session中的loginName对象
			ActionContext ac=ActionContext.getContext();
			 Map<String, Object> session = ac.getSession();
			 session.remove("loginUser");
			return "success";	
		}
		
		
		//饼状图数据
		public void chart() {
			System.out.println("饼状图数据");
			//获得测试班级的数量
			int countCei =adminService.countCei();
			//获得开发班级的数量
			int countJ=adminService.countJ();
			//数据封装
			Chart ch=new Chart();
			ch.setCountC(countCei);
			ch.setCountJ(countJ);
		    //数据发送
			try {
				PrintWriter out=ServletActionContext.getResponse().getWriter();
				//将对象转换成json对象
				ObjectMapper obj = new ObjectMapper();
				String str01=obj.writeValueAsString(ch);
				out.print(str01);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
