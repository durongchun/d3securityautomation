package com.d3security.util.excel;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.d3security.constant.TestConstant;
import com.d3security.pageobject.data.CyberSystemData;
import com.d3security.pageobject.data.PhysicAddCaseData;
import com.d3security.pageobject.data.PhysicSystemData;
import com.d3security.pageobject.data.dispatch.DispatchOfficerClearData;
import com.d3security.pageobject.data.incidentreport.IRAllElementsData;
import com.d3security.pageobject.data.incidentreport.IRGeneralInformationData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EasyExcelUtil {

	public static InputStream getResourcesFileInputStream(String fileName) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
	}

	public static String getPath() {
		return EasyExcelUtil.class.getResource("/").getPath();
	}

	public static File createNewFile(String pathName) {
		File file = new File(getPath() + pathName);
		if (file.exists()) {
			file.delete();
		} else {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
		}
		return file;
	}

	public static File readFile(String pathName) {
		return new File(getPath() + pathName);
	}

	public static File readUserHomeFile(String pathName) {
		return new File(System.getProperty("user.home") + File.separator + pathName);
	}

		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List readCyberTestDataExcel() {		
		List excelList = new ArrayList<>(); 
		EasyExcel.read(TestConstant.FILEPATH, CyberSystemData.class, new PageReadListener<CyberSystemData>(dataList -> {
			for (CyberSystemData demoData : dataList) {
                log.info("read a record{}", JSON.toJSONString(demoData));
                if (!demoData.getDescription().startsWith("//")) {
                	excelList.add(demoData);
				}
            }
			
		})).sheet("testCyber").doRead();
		return excelList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List readPhysicTestDataExcel() {		
		List excelList = new ArrayList<>(); 
		EasyExcel.read(TestConstant.FILEPATH, PhysicSystemData.class, new PageReadListener<PhysicSystemData>(dataList -> {
			for (PhysicSystemData demoData : dataList) {
                log.info("read a record{}", JSON.toJSONString(demoData));
                if (!demoData.getDescription().startsWith("//")) {
                	excelList.add(demoData);
				}
            }
			
		})).sheet("testPhysic").doRead();
		return excelList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List readPhysicCaseDataExcel() {		
		List excelList = new ArrayList<>(); 
		EasyExcel.read(TestConstant.FILEPATH, PhysicAddCaseData.class, new PageReadListener<PhysicAddCaseData>(dataList -> {
			for (PhysicAddCaseData demoData : dataList) {
                log.info("read a record{}", JSON.toJSONString(demoData));
                if (!demoData.getDescription().startsWith("//")) {
                	excelList.add(demoData);
				}                
            }
			
		})).sheet("testPhysicAddCase").doRead();
		return excelList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List readPhysicIRGeneralInformationDataExcel() {		
		List excelList = new ArrayList<>(); 
		EasyExcel.read(TestConstant.IRPATH, IRGeneralInformationData.class, new PageReadListener<IRGeneralInformationData>(dataList -> {
			for (IRGeneralInformationData data : dataList) {
                log.info("read a record{}", JSON.toJSONString(data));
                if (!data.getDescription().startsWith("//")) {
                	excelList.add(data);
				}                
            }
			
		})).sheet("test_IR_GeneralInformation").doRead();
		return excelList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List readPhysicIRAllElementsDataExcel() {		
		List excelList = new ArrayList<>(); 
		EasyExcel.read(TestConstant.IRPATH, IRAllElementsData.class, new PageReadListener<IRAllElementsData>(dataList -> {
			for (IRAllElementsData data : dataList) {
                log.info("read a record{}", JSON.toJSONString(data));
                if (!data.getDescription().startsWith("//")) {
                	excelList.add(data);
				}                
            }
			
		})).sheet("test_IR_AllElements").doRead();
		return excelList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List readPhysicDispatchOfficeClearDataExcel() {		
		List excelList = new ArrayList<>(); 
		EasyExcel.read(TestConstant.DISPATCHPATH, DispatchOfficerClearData.class, new PageReadListener<DispatchOfficerClearData>(dataList -> {
			for (DispatchOfficerClearData data : dataList) {
                log.info("read a record{}", JSON.toJSONString(data));
                if (!data.getDescription().startsWith("//")) {
                	excelList.add(data);
				}                
            }
			
		})).sheet("test_DispatchOfficerClear").doRead();
		return excelList;
	}

}
