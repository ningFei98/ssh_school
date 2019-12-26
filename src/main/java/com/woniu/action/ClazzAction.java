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
	 * ���Ұ����������༶
	 * ͨ����¼��Ϣ�еİ����ζ����idȥ���ݿ���������༶�ļ���
	 * Ҳ����ͨ����¼��Ϣ�еİ����ζ���İ༶���ϵõ������༶
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
	 * ͨ��һ���༶ID�����ݿ����һ���༶��¼���õ��༶����ѧ��
	 * dao�㷵��һ���༶���󣬰༶��������student����
	 * 
	 * Ҳ����ʹ�ð����ζ����еİ༶���ϣ���Ҫ������Ȼ���ж�ָ���༶
	 * @return
	 */
	public String findStudengtByClazzId(){
		stuList = ss.findStuByClazz(clazz);
		//clazz = cs.findStudengtByClazzId(clazz.getClazzId());
		return SUCCESS;
	}
	/**
	 * ����ѧԱת��
	 * ���ղ�ѯָ��student.action������student
	 * ��ѯ���а༶
	 * �����а��ָ��ѧ��ת�����޸�ҳ����
	 * @return
	 */
	public String findAllClazz(){
		clazzs = cs.findAllClazz();
		return SUCCESS;
	}
	
}
