package com.woniu.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.woniu.dao.IStudentDAO;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Score;
import com.woniu.pojo.Student;
@Repository
public class StudentDAOImpl implements IStudentDAO{
	@Autowired
	SessionFactory se;
	@Override
	public Student findStu(int i) {
		// TODO Auto-generated method stub
		return (Student) se.getCurrentSession().get(Student.class, i);
	}

	@Override
	public Checkin findC(int i) {
		// TODO Auto-generated method stub
		return (Checkin) se.getCurrentSession().get(Checkin.class, i);
	}

	@Override
	public Score findS(int i) {
		// TODO Auto-generated method stub
		return  (Score) se.getCurrentSession().get(Score.class, i);

	}
//	public static void main(String[] args) {
//		StudentDAOImpl s=new StudentDAOImpl();
//		s.findStu(1);
//	}
}
