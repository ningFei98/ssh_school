package com.woniu.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Student;
import com.woniu.pojo.User;
import com.woniu.service.IClazzService;
import com.woniu.service.IStudentService;

@SuppressWarnings("serial")
@Controller
public class ClazzAction extends ActionSupport{
	@Autowired
	private IClazzService cs;
	@Autowired
	private IStudentService ss;
	private List<Clazz> clazzs;
	private Clazz clazz;
	private Student student;
	private List<Student> stuList;
	
	
	

	public List<Student> getStuList() {
		return stuList;
	}


	public void setStuList(List<Student> stuList) {
		this.stuList = stuList;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public List<Clazz> getClazzs() {
		return clazzs;
	}


	public void setClazzs(List<Clazz> clazzs) {
		this.clazzs = clazzs;
	}


	public Clazz getClazz(){
		return clazz;
	}


	public void setClazz(Clazz clazz){
		this.clazz = clazz;
	}


	/**
	 * 查找班主任所属班级
	 * 通过登录信息中的班主任对象的id去数据库查找所属班级的集合
	 * 也可以通过登录信息中的班主任对象的班级集合得到所属班级
	 * @return
	 */
	public String findClazz(){
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> session = ac.getSession();
		User user =(User)session.get("loginUser");
		clazzs = cs.findClazzByOid(user.getOverman().getOvermanId());
		return SUCCESS;
	}
	
	/**
	 * 通过一个班级ID向数据库查找一条班级记录，得到班级所有学生
	 * dao层返回一个班级对象，班级对象中有student集合
	 * 
	 * 也可以使用班主任对象中的班级集合，需要遍历，然后判断指定班级
	 * @return
	 */
	public String findStudengtByClazzId(){
		stuList = ss.findStuByClazz(clazz);
		//clazz = cs.findStudengtByClazzId(clazz.getClazzId());
		return SUCCESS;
	}
	/**
	 * 操作学员转班
	 * 接收查询指定student.action传来的student
	 * 查询所有班级
	 * 将所有班和指定学生转发到修改页面面
	 * @return
	 */
	public String findAllClazz(){
		clazzs = cs.findAllClazz();
		return SUCCESS;
	}
	
}
