package com.offcn.dao;

import java.util.List;

import com.offcn.bean.Student;

public interface StudentDao {

	public List<Student> getAllStu();
	
	public int saveStu(Student student);
}
