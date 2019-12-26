package com.woniu.pojo;
// Generated 2019-12-18 21:41:06 by Hibernate Tools 5.4.7.Final

import java.util.Date;

/**
 * Score generated by hbm2java
 */
public class Score implements java.io.Serializable {

	private Integer scoreId;
	private Student student;
	private Double scoreNum;
	private Date scoreDate;

	public Score() {
	}

	public Score(Student student, Double scoreNum, Date scoreDate) {
		this.student = student;
		this.scoreNum = scoreNum;
		this.scoreDate = scoreDate;
	}

	public Integer getScoreId() {
		return this.scoreId;
	}

	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Double getScoreNum() {
		return this.scoreNum;
	}

	public void setScoreNum(Double scoreNum) {
		this.scoreNum = scoreNum;
	}

	public Date getScoreDate() {
		return this.scoreDate;
	}

	@Override
	public String toString() {
		return "Score [scoreId=" + scoreId + ", student=" + student + ", scoreNum=" + scoreNum + ", scoreDate="
				+ scoreDate + "]";
	}

	public void setScoreDate(Date scoreDate) {
		this.scoreDate = scoreDate;
	}

}
