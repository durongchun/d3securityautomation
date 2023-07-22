package com.d3security.pageobject.data.dispatch;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class DispatchAddAndEditData {	
	private String caseID;
	private String description;
	private String userName;
	private String passWord;
	private String DispatchType;
	private String OfficerID;
	private String OfficerName;
	private String SearchableTextbox;
	private String TextArea;
	
}
