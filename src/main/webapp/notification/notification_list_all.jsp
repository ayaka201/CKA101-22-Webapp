<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.webond.notification.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	NotificationService notificationSvc = new NotificationService();
    List<NotificationVO> list = notificationSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有通知資料 - listAllNotification.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有通知資料 - listAllNotification.jsp</h3>
		 <h4><a href="notification_select.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>通知編號</th>
		<th>會員編號</th>
		<th>標題</th>
		<th>通知內容</th>
		<th>通知類型</th>
		<th>是否已讀</th>
		<th>檢舉編號</th>
		<th>員工編號</th>	
		<th>更新日期</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="notificationVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${notificationVO.notificationId}</td>
			<td>${notificationVO.memberId}</td>
			<td>${notificationVO.title}</td>
			<td>${notificationVO.content}</td>
			<td>${notificationVO.notificationType}</td>
			<td>${notificationVO.isRead}</td>
			<td>${notificationVO.reportId}</td>
			<td>${notificationVO.employeeId}</td>
			<td>${notificationVO.createdAt}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/notification/notification.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="notificationId"  value="${notificationVO.notificationId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/notification/notification.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="notificationId"  value="${notificationVO.notificationId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>