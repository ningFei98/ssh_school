package com.woniu.dao;

import java.util.List;

import com.woniu.pojo.Clazz;

public interface IClazzDAO {
	
	List<Clazz> findClazzByOid(Integer id);

	Clazz findStudengtByClazzId(Integer clazzId);

	List<Clazz> findAllClazz();

}
