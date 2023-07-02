package com.holinova.pageobject.data;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class ErpSystemData {	
	private String mail;
	private String fn;
	private String ln;
	private String phone;
	private String company;
	private Date date;
	private Double Index;

}
