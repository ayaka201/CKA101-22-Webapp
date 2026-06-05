package com.webond.notification.model;

import java.util.List;

public class NotificationService {
	
	private NotificationDAO_interface dao;
	
	public NotificationService() {
		dao = new NotificationJDBCDAO();
	}

	public NotificationVO addNotification(Integer memberId, String title, String content, 
			Byte notificationType, Byte isRead, Integer reportId, Integer employeeId, java.sql.Date createdAt) {
		
		NotificationVO notificationVO = new  NotificationVO();
				
		notificationVO.setMemberId(memberId);
		notificationVO.setTitle(title);
		notificationVO.setContent(content);
		notificationVO.setNotificationType(notificationType);
		notificationVO.setIsRead(isRead);
		notificationVO.setReportId(reportId);
		notificationVO.setEmployeeId(employeeId);
		notificationVO.setCreatedAt(createdAt);
		dao.insert(notificationVO);
		
		return notificationVO;
	}

	public NotificationVO updateNotification(Integer NotificationId, Integer memberId, String title, String content, 
			Byte notificationType, Byte isRead, Integer reportId, Integer employeeId, java.sql.Date createdAt) {
		
		NotificationVO notificationVO = new  NotificationVO();

		notificationVO.setNotificationId(NotificationId);;
		notificationVO.setMemberId(memberId);
		notificationVO.setTitle(title);
		notificationVO.setContent(content);
		notificationVO.setNotificationType(notificationType);
		notificationVO.setIsRead(isRead);
		notificationVO.setReportId(reportId);
		notificationVO.setEmployeeId(employeeId);
		notificationVO.setCreatedAt(createdAt);
		dao.update(notificationVO);
		
		return notificationVO;
	}
	public void deleteNotification(Integer notificationId) {
		dao.delete(notificationId);
	}
	
	public NotificationVO getOneNotification(Integer notificationId) {
		return dao.findByPrimaryKey(notificationId);
	}
	
	public List<NotificationVO> getAll(){
		return dao.getAll();
	}
	

}
