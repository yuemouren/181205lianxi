package com.offcn.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.offcn.bean.Mobile;
import com.offcn.dao.MobileDao;

public class ThreadBatchMobile extends Thread{
	
	private MobileDao mobileDao;
	private Sheet sheet;
	
	public ThreadBatchMobile(MobileDao mobileDao, Sheet sheet) {
		this.mobileDao = mobileDao;
		this.sheet = sheet;
	}
	
	@Override
	public void run() {
		
		List<Mobile> list = new ArrayList<Mobile>();
		
		int rows = sheet.getPhysicalNumberOfRows();
		for(int i = 1;i < rows ; i++){
			Row row = sheet.getRow(i);
			String mobilenumber = row.getCell(1).getStringCellValue();
			String mobilearea = row.getCell(2).getStringCellValue();
			String mobiletype = row.getCell(3).getStringCellValue();
			String areacode = row.getCell(4).getStringCellValue();
			String postcode = row.getCell(5).getStringCellValue();
			
			Mobile mobile = new Mobile();
			mobile.setAreacode(areacode);
			mobile.setMobilearea(mobilearea);
			mobile.setMobilenumber(mobilenumber);
			mobile.setMobiletype(mobiletype);
			mobile.setPostcode(postcode);
			list.add(mobile);
			if(list.size() == 1000){
				mobileDao.saveBatchMobile(list);
				//清空list集合
				list.clear();
			}
		}
		if(list.size() > 0){
			mobileDao.saveBatchMobile(list);
			list.clear();
		}
	}	
}
