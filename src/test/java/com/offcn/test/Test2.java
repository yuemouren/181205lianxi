package com.offcn.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.offcn.bean.Mobile;
import com.offcn.dao.MobileDao;

public class Test2 {

	public static void main(String[] args) {
		
		/*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-service.xml","spring-dao.xml");
		StudentService service = context.getBean(StudentService.class);
		List<Student> allStu = service.getAllStu();
		for (Student student : allStu) {
			System.out.println(student);
		}*/
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
		MobileDao dao = context.getBean(MobileDao.class);
		
		List<Mobile> list = new ArrayList<Mobile>();
		
		Mobile mobile1 = new Mobile();
		mobile1.setMobilearea("山东 烟台");
		mobile1.setAreacode("0987");
		mobile1.setPostcode("450000");
		mobile1.setMobilenumber("1300000");
		mobile1.setMobiletype("中国联通Gsm");
		
		Mobile mobile2 = new Mobile();
		mobile2.setMobilearea("山东 青岛");
		mobile2.setAreacode("0007");
		mobile2.setPostcode("49000");
		mobile2.setMobilenumber("1300324");
		mobile2.setMobiletype("中国移动Gsm");
		
		list.add(mobile1);
		list.add(mobile2);
		
		dao.saveBatchMobile(list);
	
	}

}
