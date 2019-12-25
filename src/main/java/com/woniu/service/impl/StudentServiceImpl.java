package com.woniu.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.IStudentDAO;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Score;
import com.woniu.pojo.Student;
import com.woniu.service.IStudentService;
@Service
@Transactional
public class StudentServiceImpl implements IStudentService{
	@Autowired
	IStudentDAO its;
	@Override
	public Student findStu(int i) {
		// TODO Auto-generated method stub
		return its.findStu(i);
	}

	@Override
	public Checkin findC(int i) {
		// TODO Auto-generated method stub
		return its.findC(i);
	}

	@Override
	public Score findS(int i) {
		// TODO Auto-generated method stub
		return its.findS(i);
	}

}
