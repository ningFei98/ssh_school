package com.woniu.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.IClazzDAO;
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

	//stu
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
}
