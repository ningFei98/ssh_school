package com.woniu.dao;

import java.util.List;

import com.woniu.pojo.Score;
import com.woniu.pojo.Student;

public interface IScoreDAO {
	List<Score> findByStu(Student obj);

	void save(Score obj); 
	
	void update(Score obj);
	
	Score findByScoreId(Score obj);

}
