package com.woniu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.ICheckinDAO;
import com.woniu.dao.IScoreDAO;
import com.woniu.dao.IStudentDAO;
import com.woniu.pojo.Checkin;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Score;
import com.woniu.pojo.Student;
import com.woniu.service.IStudentService;
@Service
@Transactional
public class StudentServiceImpl implements IStudentService{
	@Autowired
	IStudentDAO sd;
	@Autowired
	IScoreDAO scd;
	@Autowired
	ICheckinDAO cd;

	@Override
	public List<Score> findScoreByClazz(Clazz obj) {
		List<Score> scores = new ArrayList<Score>();
		List<Student> list = sd.findAllByClazz(obj);
		
		for (Student student : list) {
			List<Score> ScoresOneStu = scd.findByStu(student);
			for (Score score : ScoresOneStu) {
				score.setStudent(student);
			
				scores.add(score);
			}
		}
		return scores;
	}

	@Override
	public void saveScore(Score obj) {
		// TODO Auto-generated method stub
		Date date = new Date();
		obj.setScoreDate(date);
		scd.save(obj);
	}

	@Override
	public void saveScores(List<Score> list) {
		for (Score score : list) {
			scd.save(score);
		}
	}

	@Override
	public List<Student> findStuByClazz(Clazz obj) {
		List<Student> list = sd.findAllByClazz(obj);
		return list;
	}

	@Override
	public List<Checkin> findCheckinByClazz(Clazz obj) {
		List<Checkin> chList = new ArrayList<Checkin>();
		List<Student> list = sd.findAllByClazz(obj);
		
		for (Student student : list) {
			Checkin CheckinOneStu = cd.findByStu(student);
			chList.add(CheckinOneStu);
		}
		return chList;
	}

	@Override
	public void updScore(Score obj) {
		// TODO Auto-generated method stub
		scd.update(obj);
	}

	@Override
	public Score findOneScore(Score obj) {
		// TODO Auto-generated method stub
		return scd.findByScoreId(obj);
	}

	

}
