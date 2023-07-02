package com.holinova.pageobject.locator;

import org.openqa.selenium.By;

public class RoadTestLocator {
	public static final By SIGN_IN = By.xpath("//button[contains(@class, 'mat-raised-button')]");
	public static final By TC_CHECKBOX = By.cssSelector(".mat-checkbox-inner-container");
	public static final By KEYWORD = By.xpath("//input[@formcontrolname=\"keyword\"]");
	public static final By DRIVER_LICENCENUMBER = By.xpath("//input[@formcontrolname=\"licenceNumber\"]");
	public static final By DRIVER_LN = By.xpath("//input[@formcontrolname=\"drvrLastName\"]");
	public static final By LOCATION = By.cssSelector("input[role='combobox']");
	public static final By LOCATION_TIP = By.cssSelector("mat-option:nth-of-type(1) > .mat-option-text");
	public static final By SEARCH_BUTTON = By
			.cssSelector(".mat-accent.mat-button-base.mat-raised-button.search-button");
	public static final By LANGLEY_WILLOW = By
			.xpath("//*[(@class='appointment-location-wrapper')]//*[contains(text(), 'Willowbrook Center' )]");
	public static final By DATE_TITLE = By.cssSelector(".date-title");
	public static final By TIME = By.xpath("//*[contains(text(), 'October')]/..");
	public static final By REVIEW_APPOINTMENT = By.xpath("//span[contains(text(), 'Review Appointment')]");
	public static final By NEXT = By.cssSelector(".action-buttons [color='accent']:nth-of-type(2) .mat-button-wrapper");
	public static final By SENT = By
			.cssSelector(".otp-action-buttons [color='accent']:nth-of-type(2) .mat-button-wrapper");
	public static final By RESCHEDULE = By.xpath("//*[contains(text(), 'Reschedule appointment')]");
	public static final By YES = By.xpath("//*[contains(text(), 'Yes')]");
	public static final By WILLBROOK_DIALOG = By.cssSelector(".container.dialog");
	public static final By ALLTIME = By.cssSelector("span[class=\"mat-button-toggle-label-content\"]");
	public static final By SNS_REDIOBOX = By.cssSelector("input[value='SNS']");
	public static final By ERR_TEXT = By.cssSelector("span[class=\"error-text\"]");

}
