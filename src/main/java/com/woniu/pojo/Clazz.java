package com.woniu.pojo;
// Generated 2019-12-18 21:41:06 by Hibernate Tools 5.4.7.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Clazz generated by hbm2java
 */
public class Clazz implements java.io.Serializable {

	private Integer clazzId;
	private Overman overman;
	private Stage stage;
	private Teacher teacher;
	private String clazzName;
	private String clazzType;
	private Set students = new HashSet(0);

	public Clazz() {
	}

	public Clazz(Overman overman, Stage stage, Teacher teacher, String clazzName, String clazzType, Set students) {
		this.overman = overman;
		this.stage = stage;
		this.teacher = teacher;
		this.clazzName = clazzName;
		this.clazzType = clazzType;
		this.students = students;
	}

	public Integer getClazzId() {
		return this.clazzId;
	}

	public void setClazzId(Integer clazzId) {
		this.clazzId = clazzId;
	}

	public Overman getOverman() {
		return this.overman;
	}

	public void setOverman(Overman overman) {
		this.overman = overman;
	}

	public Stage getStage() {
		return this.stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getClazzName() {
		return this.clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	public String getClazzType() {
		return this.clazzType;
	}

	public void setClazzType(String clazzType) {
		this.clazzType = clazzType;
	}

	public Set getStudents() {
		return this.students;
	}

	public void setStudents(Set students) {
		this.students = students;
	}



}
