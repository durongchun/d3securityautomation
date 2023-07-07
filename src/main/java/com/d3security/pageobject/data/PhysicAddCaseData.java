package com.d3security.pageobject.data;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class PhysicAddCaseData {	
	private String caseID;
	private String description;
	private String userName;
	private String passWord;
	private String DropDownOption;
	private String MandatoryFields;
	private String OwnerSite;

//	private Date date;
//	private Double Index;

	
	
}
