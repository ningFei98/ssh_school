package com.woniu.dao;

import java.util.List;

import com.woniu.pojo.Clazz;
import com.woniu.pojo.Student;

public interface IStudentDAO {
	List<Student> findAllByClazz(Clazz obj); 

}
