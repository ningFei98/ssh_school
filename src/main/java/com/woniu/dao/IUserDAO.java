package com.woniu.dao;


import com.woniu.pojo.User;

public interface IUserDAO {

	User findOneByUname(String uname);

	

	void upd(User u);
}
