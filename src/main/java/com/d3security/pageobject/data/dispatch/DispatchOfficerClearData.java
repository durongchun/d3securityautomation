package com.d3security.pageobject.data.dispatch;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class DispatchOfficerClearData {	
	private String caseID;
	private String description;
	private String userName;
	private String passWord;
	private String DispatchID;
	private String OfficerID;
	private String OfficerName;
	
	
}
