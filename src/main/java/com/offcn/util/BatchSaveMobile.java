package com.offcn.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.offcn.dao.MobileDao;

public class BatchSaveMobile {
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
		MobileDao mobileDao = context.getBean(MobileDao.class);
		
		try {
			Workbook workbook = WorkbookFactory.create(new FileInputStream("d:\\chart\\Mobile.xls"));
		
			int sheets = workbook.getNumberOfSheets();
			
			for(int i = 0;i < sheets ; i++){
				Sheet sheet = workbook.getSheetAt(i);
				ThreadBatchMobile tbm = new ThreadBatchMobile(mobileDao,sheet);
				tbm.start();
			}
			
		
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
