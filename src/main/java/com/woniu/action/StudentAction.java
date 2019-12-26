package com.woniu.action;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.woniu.pojo.Score;
import com.woniu.pojo.Student;
import com.woniu.pojo.Teacher;
import com.woniu.pojo.User;
import com.woniu.povo.StudentCheckintype;
import com.woniu.service.IClazzService;
import com.woniu.service.IStudentService;
import com.woniu.util.ExcelUtils;
import com.woniu.util.ExcelWriter;
import com.woniu.utils.ExcelUtil;
import com.woniu.utils.StudentExcelWriter;

@Controller
public class StudentAction extends ActionSupport{
	@Autowired
	private IStudentService ss;
	@Autowired
	private IClazzService cs;
	
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
	
	
	/**
	 * 
	 * @return
	 */
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
	/**
	 * 导出学生信息excel
	 * @return
	 */
	public String studentExcelWrite() {
		List<Student> students = ss.findStuByClazz(clazz);
		fileName = "学生信息表"+new SimpleDateFormat("yyMMddhhmmss").format(new Date())+".xlsx";
	    //设置字符，防止乱码
	    try {
			fileName= new String(fileName.getBytes("UTF-8"),"ISO8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Workbook workbook = StudentExcelWriter.exportData(students);;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
			workbook.write(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte[] ba = output.toByteArray();
        excelFile = new ByteArrayInputStream(ba);
        try {
			output.flush();
			if(output!=null) {
				output.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "exportExcel";
	}
	
	//stu模块===========================================
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
	User u=(User) ActionContext.getContext().getSession().get("loginUser");
	System.out.println(u+"qqqqqqqqqqqqq");
		stu=ss.findStu(1);
		return "seccess";
	}
	public String getC() {
		cin=ss.findC(1);
		return "seccess";
	}
	public String getS() {
		sc=ss.findS(1);
		return "seccess";
	}


	//teacher模块---------------------------------------------------------
	private List<Score> scores = new ArrayList<Score>();
	private List<Clazz> clazzs = new ArrayList<Clazz>();
	private Set<Student> students;
	private Score score;
	private File scoreExcel;
	private String scoreExcelFileName;
	private List<Student> stuList;
	private List<Checkin> checkinList;
	private static final long serialVersionUID = 1L;
	private InputStream excelFile;
	private String fileName;
	
	
	
	public InputStream getExcelFile() {
		return excelFile;
	}


	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public List<Checkin> getCheckinList() {
		return checkinList;
	}
	public void setCheckinList(List<Checkin> checkinList) {
		this.checkinList = checkinList;
	}
	public List<Student> getStuList() {
		return stuList;
	}
	public void setStuList(List<Student> stuList) {
		this.stuList = stuList;
	}
	public File getScoreExcel() {
		return scoreExcel;
	}
	public void setScoreExcel(File scoreExcel) {
		this.scoreExcel = scoreExcel;
	}
	public String getScoreExcelFileName() {
		return scoreExcelFileName;
	}
	public void setScoreExcelFileName(String scoreExcelFileName) {
		this.scoreExcelFileName = scoreExcelFileName;
	}

	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}



	public List<Clazz> getClazzs() {
		return clazzs;
	}

	public void setClazzs(List<Clazz> clazzs) {
		this.clazzs = clazzs;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		scores = scores;
	}

	public String findScoreByClazz() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("loginUser");
		Teacher teacher1 = user.getTeacher();
		Set<Clazz> clazzsSet = teacher1.getClazzs();
		for (Clazz clazz1 : clazzsSet) {
			clazz = clazz1;
		}
		scores = ss.findScoreByClazz(clazz);
		return "score";
	}
	
	public String findClazzStu() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("loginUser");
		Teacher teacher1 = user.getTeacher();
		Set<Clazz> clazzsSet = teacher1.getClazzs();
		for (Clazz clazz1 : clazzsSet) {
			students = clazz1.getStudents();
		}
		return "scoreAdd";
	}
	public String saveScore() {
		score.setStudent(student);
		ss.saveScore(score);
		return "findClassScore";
	}
	
	public String ScoresAdd() {
		List<Score> list = ExcelUtils.readExcel(scoreExcel,scoreExcelFileName);
		ss.saveScores(list);
		return "findClassScore";
	}
	public String showClazzInfo() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("loginUser");
		Teacher teacher1 = user.getTeacher();
		Set<Clazz> clazzsSet = teacher1.getClazzs();
		for (Clazz clazz1 : clazzsSet) {
			clazz = clazz1;
		}
		stuList = ss.findStuByClazz(clazz);
		return "showClazzInfo";
	}
	
	public String showClazzCheckin() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("loginUser");
		Teacher teacher1 = user.getTeacher();
		Set<Clazz> clazzsSet = teacher1.getClazzs();
		for (Clazz clazz1 : clazzsSet) {
			clazz = clazz1;
		}
		checkinList = ss.findCheckinByClazz(clazz);
		System.out.println(checkinList.size()+"==============");
		return "showClazzCheckin";
	}
	public String findOneScore() {
		score = ss.findOneScore(score);
		return "teacherUpdScoreUI";
	}
	public String updScore() {
		score.setStudent(student);
		ss.updScore(score);
		return "findClassScore";
	}
	public String ScoreExcelWrite() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("loginUser");
		Teacher teacher1 = user.getTeacher();
		Set<Clazz> clazzsSet = teacher1.getClazzs();
		for (Clazz clazz1 : clazzsSet) {
			clazz = clazz1;
		}
		scores = ss.findScoreByClazz(clazz);
		fileName = "学生成绩表"+new SimpleDateFormat("yyMMddhhmmss").format(new Date())+".xls";
	    //设置字符，防止乱码
	    try {
			fileName= new String(fileName.getBytes("UTF-8"),"ISO8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Workbook workbook = ExcelWriter.exportData(scores);;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
			workbook.write(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte[] ba = output.toByteArray();
        excelFile = new ByteArrayInputStream(ba);
        try {
			output.flush();
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        return "exportExcel";
	}
	
	
}
