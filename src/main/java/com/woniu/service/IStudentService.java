package com.woniu.service;


import java.util.List;

import com.woniu.pojo.Checkin;
import com.woniu.pojo.Checkintype;
import com.woniu.pojo.Student;

public interface IStudentService {
	List<Checkin> findChecksByStudentId(Integer studentId);
	Student getStudentByStudentId(Integer studentId);
	List<Checkintype> findAllCheckintype();
	void overSaveCheckin(Checkin check);
	void overSaveStudent(Student student);
	void overUpdStudentClazz(Student student);
	void overUpdStudent(Student student);
}
