package com.woniu.dao;


import com.woniu.pojo.User;

public interface IUserDAO {

	User findOneByUname(String uname);

	void save(User user);
//studentÄ£¿éµÄ
	void judge(int in);
}
