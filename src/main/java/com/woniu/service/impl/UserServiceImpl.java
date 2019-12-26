package com.woniu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.IUserDAO;
import com.woniu.pojo.User;
import com.woniu.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	IUserDAO dd;
	

	@Override
	public User login(User user) {
			User temp = dd.findOneByUname(user.getUserName());
			if (temp != null && user.getUserPwd().equals(temp.getUserPwd())) {
				return temp;
			}
		
		return null;
	}


	@Override
	public void upd(User u) {
		// TODO Auto-generated method stub
		dd.upd(u);
	}


	


	
}
