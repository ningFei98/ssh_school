package com.woniu.pojo;
// Generated 2019-12-18 21:41:06 by Hibernate Tools 5.4.7.Final

import java.util.Date;

/**
 * Checkin generated by hbm2java
 */
public class Checkin implements java.io.Serializable {

	private Integer checkInId;
	private Checkintype checkintype;
	private Student student;
	private Date checkInDate;

	public Checkin() {
	}

	public Checkin(Checkintype checkintype, Student student, Date checkInDate) {
		this.checkintype = checkintype;
		this.student = student;
		this.checkInDate = checkInDate;
	}

	public Integer getCheckInId() {
		return this.checkInId;
	}

	public void setCheckInId(Integer checkInId) {
		this.checkInId = checkInId;
	}

	public Checkintype getCheckintype() {
		return this.checkintype;
	}

	public void setCheckintype(Checkintype checkintype) {
		this.checkintype = checkintype;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getCheckInDate() {
		return this.checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

}