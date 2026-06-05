<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.webond.notification.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  NotificationVO notificationVO = (NotificationVO) request.getAttribute("notificationVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>通知資料 - listOneNotification.jsp</title>

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
	width: 600px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>通知資料 - listOneNotification.jsp</h3>
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
	<tr>
		<td><%=notificationVO.getNotificationId()%></td>
		<td><%=notificationVO.getMemberId()%></td>
		<td><%=notificationVO.getTitle()%></td>
		<td><%=notificationVO.getContent()%></td>
		<td><%=notificationVO.getNotificationType()%></td>
		<td><%=notificationVO.getIsRead()%></td>
		<td><%=notificationVO.getReportId()%></td>
		<td><%=notificationVO.getEmployeeId()%></td>
		<td><%=notificationVO.getCreatedAt()%></td>
	</tr>
</table>

</body>
</html>