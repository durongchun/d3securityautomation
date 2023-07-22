package com.d3security.util.dataprovider;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.d3security.util.excel.EasyExcelUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelDataProvider {

	@DataProvider(name = "CyberData")
	public static Object[][] ReadCyber() throws IOException {
		List dataList = EasyExcelUtil.readCyberTestDataExcel();
		// count my number of Rows
		int RowNum = dataList.size();
		int ColNum = 1;
		// pass my count data in array
		Object Data[][] = new Object[RowNum][ColNum];
		for (int i = 0; i < dataList.size(); i++) {
			Data[i][0] = dataList.get(i);
		}
		return Data;
	}

	@DataProvider(name = "PhysicData")
	public static Object[][] ReadPhysic() throws IOException {
		List dataList = EasyExcelUtil.readPhysicTestDataExcel();
		// count my number of Rows
		int RowNum = dataList.size();
		int ColNum = 1;
		// pass my count data in array
		Object Data[][] = new Object[RowNum][ColNum];
		for (int i = 0; i < dataList.size(); i++) {
			Data[i][0] = dataList.get(i);
		}
		return Data;
	}
	
	@DataProvider(name = "PhysicCaseData")
	public static Object[][] ReadPhysicCase() throws IOException {
		List dataList = EasyExcelUtil.readPhysicCaseDataExcel();
		// count my number of Rows
		int RowNum = dataList.size();
		int ColNum = 1;
		// pass my count data in array
		Object Data[][] = new Object[RowNum][ColNum];
		for (int i = 0; i < dataList.size(); i++) {
			Data[i][0] = dataList.get(i);
		}
		return Data;
	}
	
	@DataProvider(name = "PhysicIRGeneralInformationData")
	public static Object[][] ReadPhysicIR() throws IOException {
		List dataList = EasyExcelUtil.readPhysicIRGeneralInformationDataExcel();
		// count my number of Rows
		int RowNum = dataList.size();
		int ColNum = 1;
		// pass my count data in array
		Object Data[][] = new Object[RowNum][ColNum];
		for (int i = 0; i < dataList.size(); i++) {
			Data[i][0] = dataList.get(i);
		}
		return Data;
	}
	
	@DataProvider(name = "PhysicIRAllElementsData")
	public static Object[][] ReadPhysicIRAllElements() throws IOException {
		List dataList = EasyExcelUtil.readPhysicIRGeneralInformationDataExcel();
		// count my number of Rows
		int RowNum = dataList.size();
		int ColNum = 1;
		// pass my count data in array
		Object Data[][] = new Object[RowNum][ColNum];
		for (int i = 0; i < dataList.size(); i++) {
			Data[i][0] = dataList.get(i);
		}
		return Data;
	}
	
	@DataProvider(name = "PhysicDispatchData")
	public static Object[][] ReadPhysicDispatchData() throws IOException {
		List dataList = EasyExcelUtil.readPhysicDispatchOfficeClearDataExcel();
		// count my number of Rows
		int RowNum = dataList.size();
		int ColNum = 1;
		// pass my count data in array
		Object Data[][] = new Object[RowNum][ColNum];
		for (int i = 0; i < dataList.size(); i++) {
			Data[i][0] = dataList.get(i);
		}
		return Data;
	}
}
