package com.woniu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.IClazzDAO;
import com.woniu.pojo.Clazz;
import com.woniu.service.IClazzService;

@Service
@Transactional
public class ClazzServiceImpl implements IClazzService {
	@Autowired
	IClazzDAO cd;

	@Override
	public List<Clazz> findClazzByOid(Integer id) {
		
		return cd.findClazzByOid(id);
	}

	@Override
	public Clazz findStudengtByClazzId(Integer clazzId) {
		// TODO Auto-generated method stub
		return cd.findStudengtByClazzId(clazzId);
	}

	@Override
	public List<Clazz> findAllClazz() {
		// TODO Auto-generated method stub
		return cd.findAllClazz();
	}

}
