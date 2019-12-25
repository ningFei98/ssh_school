package com.woniu.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Score;
import com.woniu.pojo.Student;
import com.woniu.pojo.User;
import com.woniu.service.IStudentService;
import com.woniu.service.impl.StudentServiceImpl;
@Controller
public class StudentAction extends ActionSupport{
	
	@Autowired
	private IStudentService ssi;
	private Student stu;
	private Checkin cin;
	private Score sc;
	
	public Student getStu() {
		return stu;
	}
	public void setStu(Student stu) {
		this.stu = stu;
	}
	public Checkin getCin() {
		return cin;
	}
	public void setCin(Checkin cin) {
		this.cin = cin;
	}
	public Score getSc() {
		return sc;
	}
	public void setSc(Score sc) {
		this.sc = sc;
	}
	public String getSt() {
	User u=(User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	System.out.println(u+"qqqqqqqqqqqqq");
		stu=ssi.findStu(1);
		return "seccess";
	}
	public String getC() {
		cin=ssi.findC(1);
		return "seccess";
	}
	public String getS() {
		sc=ssi.findS(1);
		return "seccess";
	}
}
