package com.offcn.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.offcn.bean.Student;
import com.offcn.service.StudentService;

@Controller
public class DownloadFile {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("download")
	public String download(HttpServletRequest request,HttpServletResponse response){
		List<Student> allStu = studentService.getAllStu();
		
		String path = request.getServletContext().getRealPath("download");
		
		File file1 = new File(path);
		if(!file1.exists()){
			file1.mkdir();
		}
		
		String filename = "xxx成绩单.xlsx";
		
		File file2 = new File(path+"\\"+filename);
		
		//获取工作簿对象
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("成绩表");
		
		int rownum = 1;
		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("编号");
		row.createCell(1).setCellValue("姓名");
		row.createCell(2).setCellValue("成绩");
		row.createCell(3).setCellValue("电话号");
		
		for(Student stu : allStu){
			XSSFRow row1 = sheet.createRow(rownum);
			row1.createCell(0).setCellValue(stu.getId());
			row1.createCell(1).setCellValue(stu.getName());
			row1.createCell(2).setCellValue(stu.getScore());
			row1.createCell(3).setCellValue(stu.getPhone());
			rownum++;
		}
		
		//保存到磁盘
		try {
			workbook.write(new FileOutputStream(file2));
			workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//设置响应头(通知浏览器，服务器要返回的文件格式)
		response.setContentType("application/x-xls;charset=GBK");
		//设置浏览器下载提示
		try {
			response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(filename.getBytes(), "ISO8859-1") + "\"");
		
			//设置下载文件长度
			response.setContentLength((int)file2.length());
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(file2);
			
			int len=-1;
			byte buf[]=new byte[4096];
			
			while((len=in.read(buf))!=-1){
				out.write(buf, 0, len);
			}
			
			out.close();
			in.close();
		}catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "download-ok";
	}
	
}
