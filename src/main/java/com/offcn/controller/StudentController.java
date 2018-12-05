package com.offcn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.offcn.bean.Student;
import com.offcn.bean.StudentPie;
import com.offcn.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("getAllStu")
	@ResponseBody
	public List<Student> getAllStu(){
		
		return studentService.getAllStu();
	}
	
	@RequestMapping("getPie")
	@ResponseBody
	public List<StudentPie> getPie(){
		
		return studentService.getPie();
	}
}
