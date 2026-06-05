package com.webond.notification.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class TsetNotification {
	public static void main(String[] args) {
		NotificationJDBCDAO dao = new NotificationJDBCDAO();

//		新增
//		NotificationVO ntfy1 = new NotificationVO();
//		ntfy1.setMemberId(1);
//		ntfy1.setTitle("歡迎加入本平台");
//		ntfy1.setContent("歡迎加入本平台，您現在可以瀏覽教練服務、參與揪團活動及預約場地。如有任何問題，請隨時聯繫客服中心。");
//		ntfy1.setNotificationType((byte) 3);
//		ntfy1.setIsRead((byte) 0);
//		ntfy1.setReportId(null);
//		ntfy1.setEmployeeId(1002);
//		ntfy1.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//		


//		修改
//		NotificationVO ntfy2 = new NotificationVO();
//		ntfy2.setNotificationId(4);
//		ntfy2.setMemberId(2);
//		ntfy2.setTitle("聊天室通知");
//		ntfy2.setContent("您有未讀的聊天訊息！");
//		ntfy2.setNotificationType((byte) 4);
//		ntfy2.setIsRead((byte) 0);
//		ntfy2.setReportId(null);
//		ntfy2.setEmployeeId(1001);
//		ntfy2.setCreatedAt(Timestamp.valueOf(LocalDateTime.of(2026, 5, 30, 0, 0, 0))); 

//		刪除
		dao.delete(2);

//		單筆查詢
		NotificationVO ntfy3 = dao.findByPrimaryKey(5);
		System.out.println(ntfy3.getMemberId());
		System.out.println(ntfy3.getTitle());
		System.out.println(ntfy3.getContent());
		System.out.println(ntfy3.getNotificationType());
		System.out.println(ntfy3.getIsRead());
		System.out.println(ntfy3.getReportId());
		System.out.println(ntfy3.getEmployeeId());
		System.out.println(ntfy3.getCreatedAt());

//		查詢多筆
		List<NotificationVO> list = dao.getAll();
		for (NotificationVO notificationList : list) {
			System.out.print(notificationList.getNotificationId() + ",");
			System.out.print(notificationList.getMemberId() + ",");
			System.out.print(notificationList.getTitle() + ",");
			System.out.print(notificationList.getContent() + ",");
			System.out.print(notificationList.getNotificationType() + ",");
			System.out.print(notificationList.getIsRead() + ",");
			System.out.print(notificationList.getReportId() + ",");
			System.out.print(notificationList.getEmployeeId());
			System.out.print(notificationList.getCreatedAt() + ",");
			System.out.println();
		}

//		dao.insert(ntfy1);
//		dao.update(ntfy2);
	}

}
