package com.woniu.service;

import java.util.List;

import com.woniu.pojo.Checkin;
import com.woniu.pojo.Clazz;
import com.woniu.pojo.Score;
import com.woniu.pojo.Student;

public interface IStudentService {
	
	List<Score> findScoreByClazz(Clazz obj);

	void saveScore(Score obj);

	void saveScores(List<Score> list);

	List<Student> findStuByClazz(Clazz obj);
	
	List<Checkin> findCheckinByClazz(Clazz obj);
	
	void updScore(Score obj);
	
	Score findOneScore(Score obj);
}
