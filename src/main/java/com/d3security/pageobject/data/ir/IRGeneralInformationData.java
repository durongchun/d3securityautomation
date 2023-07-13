package com.d3security.pageobject.data.ir;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class IRGeneralInformationData {	
	private String caseID;
	private String description;
	private String userName;
	private String passWord;
	private String dropDownOption;
	private String incidentType;
	private String title;
	private String assignee;
	private String ccRecipients;
	private String ccUsers;
	private String ccGroups;
	private String notifyRecipientOnce;
	private String reportingDate;
	private String incidentOccurredOn;
	private String incidentEndedOn;
	private String sendOneTimeEmailNotificationOnSave;
	private String notifications;
	private String emailNotificationAssignmentRules ;
	private String notifyCCRecipientsOnCreateEditandClose;
	private String notifyCreator;
	private String creator_onIRCreateandEdit;
	private String creator_onIRClose;
	private String creator_onAssigneeReassign;
	private String notifyAssignee;
	private String assignee_onIRCreateandEdit;
	private String assignee_onIRClose;
	private String assignee_onAssigneeReassign;
	private String ArrestDetails;
	private String WorkplaceViolence;
	private String TypeofAccident;
	private String RequiredField;
	private String MandatoryonSave;
	private String MandatoryonClose;

	
	
}
