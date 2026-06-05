package com.webond.notification.model;

import java.util.List;

public interface NotificationDAO_interface {
	public void insert(NotificationVO notificationVO);
	public void update(NotificationVO notificationVO);
	public void delete(Integer notificationId);
	public NotificationVO findByPrimaryKey(Integer notificationId);
	public List<NotificationVO> getAll();

}
