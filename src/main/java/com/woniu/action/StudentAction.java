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
	private File userUploadFile;    //���������ϴ����ļ�

	
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
	 * ��ѯָ��ѧ����Ϣ
	 * @return
	 */
	public String getStudentByStudentId(){
		System.out.println(student.getStudentId());
		student = ss.getStudentByStudentId(student.getStudentId());
		//��ָ��ѧ�����������У�ʹ��chainת����findAllClazz.action
		ActionContext ac = ActionContext.getContext();
		ac.put("student", student);
		return SUCCESS;
	}

	/**
	 * չʾָ��ѧ��������Ϣ
	 * ͨ��һ��ѧ����ID�����ݿ⿼�ڱ��ѯָ��ѧ�����п��ڼ�¼�����Ұ�����ʱ�䵹���װ
	 * Ҳ����ͨ��һ��ѧ��id�����ݿ�鵽һ��ѧ����Ϣ��ѧ���������п��ڼ�¼set����
	 * ������Ϊset�������򣬵���ÿ�����ڼ�¼ʱ����ң�û��ʹ�ö����е�set����
	 * @return
	 */
	public String findChecksByStudentId(){
		checks = ss.findChecksByStudentId(student.getStudentId());
		return SUCCESS;
	}
	/**
	 * ��ӿ��ڼ�¼
	 * 1.���ݿ�������п������ͣ�����list���ϣ����ҳ��չʾ�������������˵���
	 * 2.���ݿ�õ�ָ��ѧ����¼�����ҳ��Ҫ�õ�ѧ��������
	 * 3.��ѧ���Ϳ�������list��װΪStudentCheckintype����povo��
	 * 4.ת�������ҳ��
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
	 * ������ӵĿ��ڼ�¼
	 * ��ת����ǰѧ����չʾ���ڼ�¼
	 * ��תʹ���ض���action��ֵ/����ת��action
	 * @return
	 */
	public String overSaveCheckin(){
		check = new Checkin();
		check.setStudent(student);
		check.setCheckintype(checkintype);
		check.setCheckInDate(new Date());
		ss.overSaveCheckin(check);
		//��ѧ��������������У�ʹ��chainת����findChecksByStudentId.action
		ActionContext ac = ActionContext.getContext();
		ac.put("student", student);
		return SUCCESS;
	}
	/**
	 * ���ѧԱ������ѧԱ
	 * @return
	 */
	public String overSaveStudent(){
		student.setClazz(clazz);
		ss.overSaveStudent(student);
		//���༶id���������У�ʹ��chainת����findChecksByStudentId.action
		ActionContext ac = ActionContext.getContext();
		ac.put("clazz", clazz);
		return SUCCESS;
	}
	
	/**
	 * ����ѧԱת����Ϣ
	 * @return
	 */
	public String overUpdStudentClazz(){
		//�������ת��id�Ž�ѧ������
		student.setClazz(clazz);
		ss.overUpdStudentClazz(student);
		return SUCCESS;
	}
	
	/**
	 * �޸�ѧԱ��Ϣ
	 * ��ѧԱ��Ϣת�����޸�ҳ��
	 * @return
	 */
	public String overUpdStudentView(){
		student = ss.getStudentByStudentId(student.getStudentId());
		return SUCCESS;
	}
	
	/**
	 * �޸�ѧԱ��Ϣ
	 * ��ҳ��ѧԱ��Ϣ����
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
		//�õ�������
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(new FileInputStream(userUploadFile));
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��ȡ��һ��sheetҳ
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
		//���༶id���������У�ʹ��chainת����findChecksByStudentId.action
		ActionContext ac = ActionContext.getContext();
		ac.put("clazz", clazz);
		return SUCCESS;
	}
	/**
	 * ����ѧ����Ϣexcel
	 * @return
	 */
	public String studentExcelWrite() {
		List<Student> students = ss.findStuByClazz(clazz);
		fileName = "ѧ����Ϣ��"+new SimpleDateFormat("yyMMddhhmmss").format(new Date())+".xlsx";
	    //�����ַ�����ֹ����
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
	
	//stuģ��===========================================
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


	//teacherģ��---------------------------------------------------------
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
		fileName = "ѧ���ɼ���"+new SimpleDateFormat("yyMMddhhmmss").format(new Date())+".xls";
	    //�����ַ�����ֹ����
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
