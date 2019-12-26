package com.woniu.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Score;
import com.woniu.pojo.Student;
import com.woniu.pojo.Teacher;
import com.woniu.pojo.User;
import com.woniu.service.IStudentService;
import com.woniu.util.ExcelUtils;

@Controller
public class StudentAction {
	@Autowired
	private IStudentService sts;
	private List<Score> scores = new ArrayList<Score>();
	private List<Clazz> clazzs = new ArrayList<Clazz>();
	private Clazz clazz;
	private Set<Student> students;
	private Student student;
	private Score score;
	private File scoreExcel;
	private String scoreExcelFileName;
	private List<Student> stuList;
	private List<Checkin> checkinList;
	
	
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
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
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

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
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
		scores = sts.findScoreByClazz(clazz);
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
		sts.saveScore(score);
		return "findClassScore";
	}
	
	public String ScoresAdd() {
		List<Score> list = ExcelUtils.readExcel(scoreExcel,scoreExcelFileName);
		sts.saveScores(list);
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
		stuList = sts.findStuByClazz(clazz);
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
		checkinList = sts.findCheckinByClazz(clazz);
		System.out.println(checkinList.size()+"==============");
		return "showClazzCheckin";
	}
	public String findOneScore() {
		score = sts.findOneScore(score);
		return "teacherUpdScoreUI";
	}
	public String updScore() {
		score.setStudent(student);
		sts.updScore(score);
		return "findClassScore";
	}
	
}
