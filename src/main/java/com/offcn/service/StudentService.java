package com.offcn.service;

import java.util.List;

import com.offcn.bean.Student;
import com.offcn.bean.StudentPie;

public interface StudentService {
	
	public List<Student> getAllStu();
	
	public List<StudentPie> getPie();
	
	public boolean saveStu(Student student);
}
