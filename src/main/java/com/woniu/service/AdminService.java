package com.woniu.service;

import java.util.List;

import com.woniu.pojo.CheckOrderBy;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Overman;
import com.woniu.pojo.Score;
import com.woniu.pojo.Teacher;

public interface AdminService {
	    List<Clazz> findAll();
	    void save(Clazz clazz);
	   
	    List<Teacher> findTeacher();
		List<Overman> findOverman();
		
		List<CheckOrderBy> findCheck(int a);
		List<Score> findScoreByClazz(int a);
		
}
