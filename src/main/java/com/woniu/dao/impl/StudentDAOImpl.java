package com.woniu.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woniu.dao.IStudentDAO;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Checkintype;
import com.woniu.pojo.Relationship1;
import com.woniu.pojo.Score;
import com.woniu.pojo.Student;
@Repository
public class StudentDAOImpl implements IStudentDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Student getStudentByStudentId(Integer studentId) {
		// TODO Auto-generated method stub
		String hql = "FROM Student WHERE student_id = ?";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql).setInteger(0, studentId);
		Student student = (Student) query.uniqueResult();
		return student;
	}
	
	@Override
	public List<Checkin> findChecksByStudentId(Integer studentId) {
		// TODO Auto-generated method stub
		String hql = "FROM Checkin WHERE student_id = ? ORDER BY checkin_date DESC";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql).setInteger(0, studentId);
		List<Checkin> checks = query.list();
		return checks;
	}

	@Override
	public List<Checkintype> findAllCheckintype() {
		String hql = "FROM Checkintype";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql);
		List<Checkintype> checkintypes = query.list();
		return checkintypes;
	}

	@Override
	public void overSaveCheckin(Checkin check) {
		sessionFactory.getCurrentSession().save(check);
	}

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(student);
	}
	
	@Override
	public Student getStudentByStudentPhone(String studentPhone) {
		// TODO Auto-generated method stub
		String hql = "FROM Student WHERE student_phone = ?";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql).setString(0, studentPhone);
		Student student = (Student) query.uniqueResult();
		return student;
	}

	@Override
	public void overSaveStudent(Relationship1 r1) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(r1);
	}

	@Override
	public void overUpdStudentClazz(Student student) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(student);
	}

	@Override
	public void overUpdStudent(Student student) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(student);
	}
	
	//stuÄ£¿é
	@Override
	public Student findStu(int i) {
		// TODO Auto-generated method stub
		return (Student) sessionFactory.getCurrentSession().get(Student.class, i);
	}

	@Override
	public Checkin findC(int i) {
		// TODO Auto-generated method stub
		return (Checkin) sessionFactory.getCurrentSession().get(Checkin.class, i);
	}

	@Override
	public Score findS(int i) {
		// TODO Auto-generated method stub
		return  (Score) sessionFactory.getCurrentSession().get(Score.class, i);

	}
//	public static void main(String[] args) {
//		StudentDAOImpl s=new StudentDAOImpl();
//		s.findStu(1);
//	}
}
