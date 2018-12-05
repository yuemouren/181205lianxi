package com.offcn.bean;

import java.io.Serializable;

public class Student implements Serializable {
	
	private Integer id;
	private String name;
	private Float score;
	private String phone;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", score=" + score + ", phone=" + phone + "]";
	}
	
	
}
