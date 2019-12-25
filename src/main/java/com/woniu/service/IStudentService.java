package com.woniu.service;

import com.woniu.pojo.Checkin;
import com.woniu.pojo.Score;
import com.woniu.pojo.Student;

public interface IStudentService {
	Student findStu(int i);
	Checkin findC(int i);
	Score findS(int i);
}
