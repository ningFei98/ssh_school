package com.woniu.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.woniu.dao.AdminDao;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Overman;
import com.woniu.pojo.Score;
import com.woniu.pojo.Student;
import com.woniu.pojo.Teacher;

@Controller
public class AdminDaoImpl implements AdminDao {
    @Autowired
    SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<Clazz> findAll() {
		System.out.println("----------------impl--findAll");
		return (List<Clazz>) sessionFactory.getCurrentSession().createCriteria(Clazz.class).list();

	}

	@Override
	public void save(Clazz clazz) {
		sessionFactory.getCurrentSession().save(clazz);  
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> findAllTeacher() {
		System.out.println("----------------dao--findAllTeacher");
		return sessionFactory.getCurrentSession().createCriteria(Teacher.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Overman> findAllOverman() {
		System.out.println("----------------dao--findAllOverman");
		return sessionFactory.getCurrentSession().createCriteria(Overman.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findAllStudent(int a) {
		String str="select * from student where clazz_id="+a;
		return (List<Student>) sessionFactory.getCurrentSession().createSQLQuery(str).addEntity(Student.class).list();
		
	}

	//查询班级考勤
	@SuppressWarnings("unchecked")
	@Override
	public List<Checkin> findOne(int id) {
		//注意，通过get查询一个对象时id只能是该表的主键
		String str="select * from checkin where student_id="+id;
		return (List<Checkin>) sessionFactory.getCurrentSession().createSQLQuery(str).addEntity(Checkin.class).list();
		
		
	}

	//查询班级分数
	@SuppressWarnings("unchecked")
	@Override
	public List<Score> findByStu(int studentId) {
		String str="select * from score where student_id="+studentId;
		return (List<Score>)sessionFactory.getCurrentSession().createSQLQuery(str).addEntity(Score.class).list();
	}
	
	//查询班级表
	@SuppressWarnings("unchecked")
	public List<Clazz> findClazz(String sql){
		System.out.println(sql);
		List<Clazz> list = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Clazz.class).list();
		System.out.println(list);
		return list;	
	}

}
