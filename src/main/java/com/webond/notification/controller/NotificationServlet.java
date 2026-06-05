package com.webond.notification.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.webond.notification.model.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/notification/notification.do")
public class NotificationServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
//單筆查詢
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("notification_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入通知編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/notification/notification_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer notification_id = null;
				try {
					notification_id = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("通知編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/notification/notification_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				NotificationService notificationSvc = new NotificationService();
				NotificationVO notificationVO = notificationSvc.getOneNotification(notification_id);
				if (notificationVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/notification/notification_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("notificationVO", notificationVO); // 資料庫取出的empVO物件,存入req
				String url = "/notification/notification_list_one.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		

//更新
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer notification_id = Integer.valueOf(req.getParameter("notificationId"));
				
				/***************************2.開始查詢資料****************************************/
				NotificationService notificationSvc = new NotificationService();
				NotificationVO notificationVO = notificationSvc.getOneNotification(notification_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("notificationVO",notificationVO);         // 資料庫取出的empVO物件,存入req
				String url = "/notification/notification_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { 
			// 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer notification_id = Integer.valueOf(req.getParameter("notification_id").trim());
			
//文字驗證
//			String ename = req.getParameter("ename");
//			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
				Integer member_id = null;
				try {
					member_id = Integer.valueOf(req.getParameter("memberId").trim());
				}catch(NumberFormatException e) {
					member_id = 0;
					errorMsgs.add("請填寫會員編號");
				}
				

				String title = req.getParameter("title").trim();
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}	
				
				String content = req.getParameter("content").trim();
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}	

				Byte notification_type = null;
				try {
					notification_type = Byte.valueOf(req.getParameter("notificationType").trim());
				} catch (NumberFormatException e) {
					notification_type = 0;
					errorMsgs.add("請填數字");
				}

				String isReadParam = req.getParameter("is_read");
				Byte is_read = null;
				if (isReadParam != null && isReadParam.trim().length() > 0) {
				    is_read = Byte.valueOf(isReadParam.trim());
				} else {
				    is_read = 0; // 完全防呆，如果連舊資料都抓不到，預設為未讀(0)
				}
				

				Integer report_id = null;
				String reportIdParam = req.getParameter("reportId");
				// 加上 !reportIdParam.equalsIgnoreCase("null") 的防呆
				if (reportIdParam != null && reportIdParam.trim().length() > 0 && !reportIdParam.equalsIgnoreCase("null")) {
				    try {
				        int tempId = Integer.parseInt(reportIdParam.trim());
				        if (tempId > 0) {
				            report_id = tempId; 
				        }
				    } catch (NumberFormatException e) {
				        errorMsgs.add("檢舉編號格式不正確");
				    }
				}

				Integer employee_id = null;
				try {
					employee_id = Integer.valueOf(req.getParameter("employeeId").trim());
				} catch (NumberFormatException e) {
					employee_id = 0;
					errorMsgs.add("請填員工編號");
				}
				
				java.sql.Date created_at = null;
				
					created_at = java.sql.Date.valueOf(req.getParameter("createdAt").trim());
				

//				java.sql.Date created_at = java.sql.Date.valueOf(req.getParameter("createdAt").trim());


				NotificationVO notificationVO = new NotificationVO();
				notificationVO.setNotificationId(notification_id);
				notificationVO.setMemberId(member_id);
				notificationVO.setTitle(title);
				notificationVO.setContent(content);
				notificationVO.setNotificationType(notification_type);
				notificationVO.setIsRead(is_read);
				notificationVO.setReportId(report_id);
				notificationVO.setEmployeeId(employee_id);
				notificationVO.setCreatedAt(created_at);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("notificationVO", notificationVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/notification/notification_update.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				NotificationService notificationSvc = new NotificationService();
				notificationVO = notificationSvc.updateNotification(notification_id, member_id, title, content, notification_type, is_read, report_id, employee_id, created_at);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("notificationVO", notificationVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/notification/notification_list_one.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		
//新增
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer member_id = null;
			try {
				member_id = Integer.valueOf(req.getParameter("memberId").trim());
			} catch (NumberFormatException e) {
				member_id = 0;
				errorMsgs.add("請填入會員編號");
			}
			
			
			String title = req.getParameter("title").trim();
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				
				
			String content = req.getParameter("content").trim();
			if (content == null || content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
			}	
			
			Byte notification_type = null;
			try {
				notification_type = Byte.valueOf(req.getParameter("notificationType").trim());
			} catch (NumberFormatException e) {
				notification_type = 0;
				errorMsgs.add("請選取通知類型");
			}
			
			Byte is_read = null;
			try {
				is_read = Byte.valueOf(req.getParameter("isRead").trim());
			} catch (NumberFormatException e) {
				is_read = 0;
				errorMsgs.add("請選取是否已讀");
			}
			
			Integer report_id = null;
			String reportIdParam = req.getParameter("reportId");
			if (reportIdParam != null && reportIdParam.trim().length() > 0) {
			    try {
			        int tempId = Integer.parseInt(reportIdParam.trim());
			        if (tempId > 0) {
			            report_id = tempId; // 只有大於 0 的合法 ID 才採用
			        }
			    } catch (NumberFormatException e) {
			        errorMsgs.add("檢舉編號格式不正確");
			    }
			}

			Integer employee_id = null;
			try {
				employee_id = Integer.valueOf(req.getParameter("employeeId").trim());
			} catch (NumberFormatException e) {
				employee_id = 0;
				errorMsgs.add("請填入員工編號");
			}
					
			java.sql.Date created_at = null;
			try {
				created_at = java.sql.Date.valueOf(req.getParameter("createdAt").trim());
			} catch (IllegalArgumentException e) {
				created_at = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
				
				
				

				NotificationVO notificationVO = new NotificationVO();
				notificationVO.setMemberId(member_id);
				notificationVO.setTitle(title);
				notificationVO.setContent(content);
				notificationVO.setNotificationType(notification_type);
				notificationVO.setIsRead(is_read);
				notificationVO.setReportId(report_id);
				notificationVO.setEmployeeId(employee_id);
				notificationVO.setCreatedAt(created_at);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("notificationVO", notificationVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/notification/notification_add.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				NotificationService NotificationSvc = new NotificationService();
				notificationVO = NotificationSvc.addNotification(member_id, title, content, notification_type, is_read, report_id, employee_id, created_at);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/notification/notification_list_all.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer notification_id = Integer.valueOf(req.getParameter("notificationId"));
				
				/***************************2.開始刪除資料***************************************/
				NotificationService notificationSvc = new NotificationService();
				notificationSvc.deleteNotification(notification_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/notification/notification_list_all.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
