package com.offcn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcn.bean.Student;
import com.offcn.bean.StudentPie;
import com.offcn.dao.StudentDao;
import com.offcn.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public List<Student> getAllStu() {
		
		return studentDao.getAllStu();
	}

	@Override
	public List<StudentPie> getPie() {
		List<Student> allStu = studentDao.getAllStu();
		List<StudentPie> listp = new ArrayList<StudentPie>();
		for (Student student : allStu) {
			StudentPie studentPie = new StudentPie();
			studentPie.setName(student.getName());
			studentPie.setValue(student.getScore());
			listp.add(studentPie);
		}
		return listp;
	}

	@Override
	public boolean saveStu(Student student) {
		int saveStu = studentDao.saveStu(student);
		if(saveStu > 0){
			return true;
		}
		return false;
	}

}
