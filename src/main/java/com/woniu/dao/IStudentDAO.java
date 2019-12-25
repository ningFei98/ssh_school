package com.woniu.dao;


import java.util.List;

import com.woniu.pojo.Checkin;
import com.woniu.pojo.Checkintype;
import com.woniu.pojo.Relationship1;
import com.woniu.pojo.Student;

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

}
