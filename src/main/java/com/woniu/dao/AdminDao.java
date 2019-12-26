package com.woniu.dao;

import java.util.List;

import com.woniu.pojo.Checkin;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Overman;
import com.woniu.pojo.Score;
import com.woniu.pojo.Student;
import com.woniu.pojo.Teacher;

public interface AdminDao {
   List<Clazz> findAll();
   void save(Clazz clazz);
   
   List<Teacher> findAllTeacher();
   List<Overman> findAllOverman();
   
   
    List<Student> findAllStudent(int a);
	List<Checkin> findOne(int studentId);
	List<Score> findByStu(int studentId);
}
