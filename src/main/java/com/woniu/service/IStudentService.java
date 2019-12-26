package com.woniu.service;


import java.util.List;

import com.woniu.pojo.Checkin;
import com.woniu.pojo.Checkintype;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Score;
import com.woniu.pojo.Student;

public interface IStudentService {
	//overmanÄ£¿é=========================
	List<Checkin> findChecksByStudentId(Integer studentId);
	Student getStudentByStudentId(Integer studentId);
	List<Checkintype> findAllCheckintype();
	void overSaveCheckin(Checkin check);
	void overSaveStudent(Student student);
	void overUpdStudentClazz(Student student);
	void overUpdStudent(Student student);
	void overDeleteStudentByStudentId(Integer studentId);
	//stuÄ£¿é---------
	Student findStu(int i);
	Checkin findC(int i);
	Score findS(int i);


//teacherÄ£¿é---------------
	List<Score> findScoreByClazz(Clazz obj);

	void saveScore(Score obj);

	void saveScores(List<Score> list);

	List<Student> findStuByClazz(Clazz obj);
	
	List<Checkin> findCheckinByClazz(Clazz obj);
	
	void updScore(Score obj);
	
	Score findOneScore(Score obj);
}
