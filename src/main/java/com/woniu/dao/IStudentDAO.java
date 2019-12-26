package com.woniu.dao;


import java.util.List;

import com.woniu.pojo.Checkin;
import com.woniu.pojo.Checkintype;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Relationship1;
import com.woniu.pojo.Student;


import com.woniu.pojo.Score;

public interface IStudentDAO {
	
	List<Checkin> findChecksByStudentId(Integer studentId);
	Student getStudentByStudentId(Integer studentId);
	List<Checkintype> findAllCheckintype();
	void overSaveCheckin(Checkin check);
	void save(Student student);
	Student getStudentByStudentPhone(String studentPhone);
	void overSaveStudent(Relationship1 r1);
	void overUpdStudentClazz(Student student);
	void overUpdStudent(Student student);

	//stuÄ£¿é
	Student findStu(int i);
	Checkin findC(int i);
	Score findS(int i);
	//teacherÄ£¿é
	List<Student> findAllByClazz(Clazz obj); 

}