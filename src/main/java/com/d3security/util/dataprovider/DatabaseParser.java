package com.d3security.util.dataprovider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.alibaba.excel.util.Validate;
import com.d3security.basepage.BasePage;

import lombok.extern.log4j.Log4j;

@Log4j
public class DatabaseParser extends BasePage {
	private static final String USER_SQL = "SELECT *  FROM  auser WHERE username = 'rongchundu'";

	public DatabaseParser(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void validateDatabaseResponseData() {
		List<Map<String, Object>> responseData = SQLDataProvider.executeSql(USER_SQL);
		List<Object> list = new ArrayList<Object>();
		for (Map<String, Object> i : responseData) {
			list.addAll(i.values());
			log.info("listxxxxxx:" + list);
		}

		Validate.isTrue(list.contains("rongchundu"));
	}

}
