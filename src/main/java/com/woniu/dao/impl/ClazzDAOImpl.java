package com.woniu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woniu.dao.IClazzDAO;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.User;
@Repository
public class ClazzDAOImpl implements IClazzDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Clazz> findClazzByOid(Integer id) {
		// TODO Auto-generated method stub
		String hql = "FROM Clazz WHERE overman_id = ?";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql).setInteger(0, id);
		List<Clazz> clazzs = query.list();
		return clazzs;
	}

	@Override
	public Clazz findStudengtByClazzId(Integer clazzId) {
		// TODO Auto-generated method stub
				String hql = "FROM Clazz WHERE clazz_id = ?";
				Query query =  sessionFactory.getCurrentSession().createQuery(hql).setInteger(0, clazzId);
				Clazz clazz = (Clazz) query.uniqueResult();
				return clazz;
	}

	@Override
	public List<Clazz> findAllClazz() {
		// TODO Auto-generated method stub
		String hql = "FROM Clazz";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql);
		List<Clazz> clazzs = query.list();
		return clazzs;
	}

}
