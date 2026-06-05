package com.webond.notification.model;

import java.sql.Date;

public class NotificationVO implements java.io.Serializable {
	private Integer notificationId;
	private Integer memberId;
	private String title;
	private String content;
	private Byte notificationType;
	private Byte isRead;
	private Date createdAt;
	private Integer reportId;
	private Integer employeeId;

	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Byte getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(Byte notificationType) {
		this.notificationType = notificationType;
	}

	public Byte getIsRead() {
		return isRead;
	}

	public void setIsRead(Byte isRead) {
		this.isRead = isRead;
	}


	public Date getCreatedAt() {
		return createdAt; 
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt; 
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

}
