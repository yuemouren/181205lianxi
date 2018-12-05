package com.offcn.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.offcn.bean.Student;
import com.offcn.dao.StudentDao;

public class Test1 {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
		StudentDao dao = context.getBean(StudentDao.class);
		List<Student> allStu = dao.getAllStu();
		for (Student student : allStu) {
			System.out.println(student);
		}
	}

}
