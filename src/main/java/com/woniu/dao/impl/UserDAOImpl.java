package com.woniu.dao.impl;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woniu.dao.IUserDAO;
import com.woniu.pojo.User;
@Repository
public class UserDAOImpl implements IUserDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	
	@Override
	public User findOneByUname(String uname) {
		// TODO Auto-generated method stub
		String hql = "FROM User WHERE user_name = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setString(0, uname);
		User user = (User) query.uniqueResult();
		return user;
	}

	

}
