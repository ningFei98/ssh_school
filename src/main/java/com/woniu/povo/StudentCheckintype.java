package com.woniu.povo;

import java.util.List;

import com.woniu.pojo.Checkintype;
import com.woniu.pojo.Student;

public class StudentCheckintype {
	private Student student;
	private List<Checkintype> checkintypes;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<Checkintype> getCheckintypes() {
		return checkintypes;
	}
	public void setCheckintypes(List<Checkintype> checkintypes) {
		this.checkintypes = checkintypes;
	}

	

}
