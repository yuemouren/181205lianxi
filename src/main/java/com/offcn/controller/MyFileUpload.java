package com.offcn.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.offcn.bean.Student;
import com.offcn.service.StudentService;

@Controller
public class MyFileUpload {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("myUpload")
	public String myUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request){
		
		//获取服务器路径
		String path = request.getServletContext().getRealPath("upload");
		
		File file1 = new File(path);
		if(!file1.exists()){
			file1.mkdir();
		}
		
		String filename = file.getOriginalFilename();
		File file2 = new  File(path +"\\"+filename);
		try {
			file.transferTo(file2);
			readExcel(file2);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "upload-ok";
	}
	
	public void readExcel(File file){
		//1.获取工作簿
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(file);
			//2.获取工作表
			Sheet sheet = workbook.getSheet("成绩表");
			
			//获取有值的行的数量
			int rows = sheet.getPhysicalNumberOfRows();
			
			for(int i = 1; i < rows;i++){
				Row row = sheet.getRow(i);
				String name = row.getCell(1).getStringCellValue();
				float score = (float) row.getCell(2).getNumericCellValue();	
				long phone = (long) row.getCell(3).getNumericCellValue();
				
				Student stu =  new Student();
				stu.setName(name);
				stu.setScore(score);
				stu.setPhone(phone+"");	
				boolean saveStu = studentService.saveStu(stu);
				if(saveStu){
					System.out.println("保存成功");
				}
			}
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(workbook != null){
				try {
					workbook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
