package com.woniu.service;

import java.util.List;

import com.woniu.pojo.Clazz;

public interface IClazzService {
	
	List<Clazz> findClazzByOid(Integer id);
	Clazz findStudengtByClazzId(Integer clazzId);
	List<Clazz> findAllClazz();
}
