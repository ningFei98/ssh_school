package com.woniu.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Checkintype;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Student;
import com.woniu.povo.StudentCheckintype;
import com.woniu.service.IStudentService;
import com.woniu.utils.ExcelUtil;

@SuppressWarnings("serial")
@Controller
public class StudentAction extends ActionSupport{
	@Autowired
	private IStudentService ss;
	
	private Student student;
	private List<Checkin> checks;
	private Checkin check;
	private StudentCheckintype sct;
	private Checkintype checkintype;
	private Clazz clazz;
	private File userUploadFile;    //用来接收上传的文件

	
	public File getUserUploadFile() {
		return userUploadFile;
	}


	public void setUserUploadFile(File userUploadFile) {
		this.userUploadFile = userUploadFile;
	}


	public Clazz getClazz() {
		return clazz;
	}


	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}


	public Checkintype getCheckintype() {
		return checkintype;
	}


	public void setCheckintype(Checkintype checkintype) {
		this.checkintype = checkintype;
	}


	public StudentCheckintype getSct() {
		return sct;
	}


	public void setSct(StudentCheckintype sct) {
		this.sct = sct;
	}


	public Checkin getCheck() {
		return check;
	}


	public void setCheck(Checkin check) {
		this.check = check;
	}


	public List<Checkin> getChecks() {
		return checks;
	}


	public void setChecks(List<Checkin> checks) {
		this.checks = checks;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * 查询指定学生信息
	 * @return
	 */
	public String getStudentByStudentId(){
		System.out.println(student.getStudentId());
		student = ss.getStudentByStudentId(student.getStudentId());
		//将指定学生放入请求中，使用chain转发到findAllClazz.action
		ActionContext ac = ActionContext.getContext();
		ac.put("student", student);
		return SUCCESS;
	}

	/**
	 * 展示指定学生考勤信息
	 * 通过一个学生的ID向数据库考勤表查询指定学生所有考勤记录，并且按考勤时间倒序封装
	 * 也可以通过一个学生id向数据库查到一条学生信息，学生对象中有考勤记录set集合
	 * 但是因为set集合无序，导致每条考勤记录时间混乱，没有使用对象中的set集合
	 * @return
	 */
	public String findChecksByStudentId(){
		checks = ss.findChecksByStudentId(student.getStudentId());
		return SUCCESS;
	}
	/**
	 * 添加考勤记录
	 * 1.数据库查找所有考勤类型，返回list集合（添加页面展示考勤类型下拉菜单）
	 * 2.数据库得到指定学生记录（添加页面要用到学生姓名）
	 * 3.将学生和考勤类型list封装为StudentCheckintype对象（povo）
	 * 4.转发到添加页面
	 * @return
	 */
	public String findAllCheckintype(){
		student = ss.getStudentByStudentId(student.getStudentId());
		sct = new StudentCheckintype();
		sct.setStudent(student);
		sct.setCheckintypes(ss.findAllCheckintype());
		return SUCCESS;
	}
	/**
	 * 保存添加的考勤记录
	 * 跳转到当前学生的展示考勤记录
	 * 跳转使用重定向action传值/请求转发action
	 * @return
	 */
	public String overSaveCheckin(){
		check = new Checkin();
		check.setStudent(student);
		check.setCheckintype(checkintype);
		check.setCheckInDate(new Date());
		ss.overSaveCheckin(check);
		//将学生对象放入请求中，使用chain转发到findChecksByStudentId.action
		ActionContext ac = ActionContext.getContext();
		ac.put("student", student);
		return SUCCESS;
	}
	/**
	 * 添加学员，保存学员
	 * @return
	 */
	public String overSaveStudent(){
		student.setClazz(clazz);
		ss.overSaveStudent(student);
		//将班级id放入请求中，使用chain转发到findChecksByStudentId.action
		ActionContext ac = ActionContext.getContext();
		ac.put("clazz", clazz);
		return SUCCESS;
	}
	
	/**
	 * 保存学员转班信息
	 * @return
	 */
	public String overUpdStudentClazz(){
		//将界面的转班id放进学生对象
		student.setClazz(clazz);
		ss.overUpdStudentClazz(student);
		return SUCCESS;
	}
	
	/**
	 * 修改学员信息
	 * 将学员信息转发到修改页面
	 * @return
	 */
	public String overUpdStudentView(){
		student = ss.getStudentByStudentId(student.getStudentId());
		return SUCCESS;
	}
	
	/**
	 * 修改学员信息
	 * 将页面学员信息保存
	 * @return
	 */
	public String overUpdStudent(){
		ss.overUpdStudent(student);
		ActionContext ac = ActionContext.getContext();
		ac.put("clazz", clazz);
		return SUCCESS;
	}
	
	public String overBatchAddStudentSave(){
		//得到工作簿
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(new FileInputStream(userUploadFile));
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取第一个sheet页
        Sheet sheet =  wb.getSheetAt(0);
        if(sheet != null){
            for(int rowNum=1; rowNum<=sheet.getLastRowNum(); rowNum++){
                Row row = sheet.getRow(rowNum);
                if(row == null){
                    continue;
                }
                student = new Student();
                student.setClazz(clazz);
                student.setStudentName(ExcelUtil.formatCell(row.getCell(1)));
                student.setStudentSex(ExcelUtil.formatCell(row.getCell(2)));
                student.setStudentPhone(ExcelUtil.formatCell(row.getCell(3)));
                student.setStudentAddress(ExcelUtil.formatCell(row.getCell(4)));
                student.setStudentEdu(ExcelUtil.formatCell(row.getCell(5)));
                student.setStudentCollege(ExcelUtil.formatCell(row.getCell(6)));
                student.setStudentProfessional(ExcelUtil.formatCell(row.getCell(7)));
                ss.overSaveStudent(student);
            }
        }
		//将班级id放入请求中，使用chain转发到findChecksByStudentId.action
		ActionContext ac = ActionContext.getContext();
		ac.put("clazz", clazz);
		return SUCCESS;
	}
	
}
