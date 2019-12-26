package com.woniu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woniu.dao.IScoreDAO;
import com.woniu.pojo.Score;
import com.woniu.pojo.Student;
@Repository
public class ScoreDAOImpl implements IScoreDAO {
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Score> findByStu(Student obj) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Score where student_id ="+obj.getStudentId());
		return query.list();
	}

	@Override
	public void save(Score obj) {
		sessionFactory.getCurrentSession().save(obj);
	}

	@Override
	public void update(Score obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public Score findByScoreId(Score obj) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Score where score_id ="+obj.getScoreId());
		return (Score) query.uniqueResult();
	}
	

}
