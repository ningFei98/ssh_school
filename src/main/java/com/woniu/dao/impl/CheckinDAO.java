package com.woniu.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woniu.dao.ICheckinDAO;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Student;
@Repository
public class CheckinDAO implements ICheckinDAO {
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public Checkin findByStu(Student obj) {
		String sql = "SELECT * FROM checkin WHERE student_id = "+obj.getStudentId()+" ORDER BY checkIn_date desc limit 0,1";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Checkin.class);
		return (Checkin) query.uniqueResult();
	}
	

}
