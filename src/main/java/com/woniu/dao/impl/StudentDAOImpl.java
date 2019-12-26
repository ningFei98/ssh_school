package com.woniu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woniu.dao.IStudentDAO;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Student;
@Repository
public class StudentDAOImpl implements IStudentDAO {
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public List<Student> findAllByClazz(Clazz clazz) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Student where clazz_id ="+clazz.getClazzId());
		return query.list();
	}
	

}
