package com.woniu.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.ICheckinDAO;
import com.woniu.dao.IClazzDAO;
import com.woniu.dao.IScoreDAO;
import com.woniu.dao.IStudentDAO;
import com.woniu.dao.IUserDAO;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Checkintype;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Relationship1;
import com.woniu.pojo.Role;
import com.woniu.pojo.Score;
import com.woniu.pojo.Student;
import com.woniu.pojo.User;
import com.woniu.service.IStudentService;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {
	@Autowired
	IStudentDAO sd;
	@Autowired
	IUserDAO ud;
	@Autowired
	IClazzDAO cd;

	//overman模块===============================================
	@Override
	public Student getStudentByStudentId(Integer studentId) {
		return sd.getStudentByStudentId(studentId);
	}

	@Override
	public List<Checkin> findChecksByStudentId(Integer studentId) {
		return sd.findChecksByStudentId(studentId);
	}

	@Override
	public List<Checkintype> findAllCheckintype() {
		// TODO Auto-generated method stub
		return sd.findAllCheckintype();
	}

	@Override
	public void overSaveCheckin(Checkin check) {
		// TODO Auto-generated method stub
		sd.overSaveCheckin(check);
	}

	@Override
	public void overSaveStudent(Student student) {
		User user = new User();
		user.setUserName(student.getStudentPhone());
		user.setUserPwd("123");
		ud.save(user);
		user = ud.findOneByUname(student.getStudentPhone());
		student.setUser(user);
		student.setStudentGraduateTime(new Date());
		student.setSutdentCode("WN" + user.getUserId());
		sd.save(student);
		student = sd.getStudentByStudentPhone(student.getStudentPhone());
		user.setStudent(student);
		Role role = new Role();
		role.setRoleId(4);
		Relationship1 r1 = new Relationship1();
		r1.setRole(role);
		r1.setUser(user);
		sd.overSaveStudent(r1);
	}

	@Override
	public void overUpdStudentClazz(Student student) {
		// TODO Auto-generated method stub
		//通过班级ID查到一个班
		Clazz clazz = cd.findStudengtByClazzId(student.getClazz().getClazzId());
		//通过学生id查到一个学生
		student = sd.getStudentByStudentId(student.getStudentId());
		student.setClazz(clazz);
		sd.overUpdStudentClazz(student);
	}

	/**
	 * 保存修改的学生信息
	 */
	@Override
	public void overUpdStudent(Student student) {
		// TODO Auto-generated method stub
		Student temp = sd.getStudentByStudentId(student.getStudentId());
		student.setClazz(temp.getClazz());
		student.setUser(temp.getUser());
		student.setSutdentCode(temp.getSutdentCode());
		student.setStudentGraduateTime(temp.getStudentGraduateTime());
		sd.overUpdStudent(student);
	}
	
	@Override
	public void overDeleteStudentByStudentId(Integer studentId) {
		sd.overDeleteStudentByStudentId(studentId);
	}


	//stu模块-------------------------------------------------------
	@Override
	public Student findStu(int i) {
		// TODO Auto-generated method stub
		return sd.findStu(i);
	}

	@Override
	public Checkin findC(int i) {
		// TODO Auto-generated method stub
		return sd.findC(i);
	}

	@Override
	public Score findS(int i) {
		// TODO Auto-generated method stub
		return sd.findS(i);
	}

	//teacher模块---------------------------------------------------------
	@Autowired
	IScoreDAO scd;
	@Autowired
	ICheckinDAO ckd;

	@Override
	public List<Score> findScoreByClazz(Clazz obj) {
		List<Score> scores = new ArrayList<Score>();
		List<Student> list = sd.findAllByClazz(obj);
		
		for (Student student : list) {
			List<Score> ScoresOneStu = scd.findByStu(student);
			for (Score score : ScoresOneStu) {
				score.setStudent(student);
			
				scores.add(score);
			}
		}
		return scores;
	}

	@Override
	public void saveScore(Score obj) {
		// TODO Auto-generated method stub
		Date date = new Date();
		obj.setScoreDate(date);
		scd.save(obj);
	}

	@Override
	public void saveScores(List<Score> list) {
		for (Score score : list) {
			scd.save(score);
		}
	}

	@Override
	public List<Student> findStuByClazz(Clazz obj) {
		List<Student> list = sd.findAllByClazz(obj);
		return list;
	}

	@Override
	public List<Checkin> findCheckinByClazz(Clazz obj) {
		List<Checkin> chList = new ArrayList<Checkin>();
		List<Student> list = sd.findAllByClazz(obj);
		
		for (Student student : list) {
			Checkin CheckinOneStu = ckd.findByStu(student);
			chList.add(CheckinOneStu);
		}
		return chList;
	}

	@Override
	public void updScore(Score obj) {
		// TODO Auto-generated method stub
		scd.update(obj);
	}

	@Override
	public Score findOneScore(Score obj) {
		// TODO Auto-generated method stub
		return scd.findByScoreId(obj);
	}

	

}
