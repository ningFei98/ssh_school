package com.woniu.dao;

import com.woniu.pojo.Checkin;
import com.woniu.pojo.Student;

public interface ICheckinDAO {
	Checkin findByStu(Student obj);
}
