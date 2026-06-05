<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>



<html>
<head>
<title>Webond Notification : HOME</title>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Roboto+Mono:ital,wght@0,100..700;1,100..700&display=swap')
	;
</style>

<style>
* {
	font-family: "Roboto Mono", monospace;
	font-optical-sizing: auto;
	font-style: normal;
}

li {
	list-style: none;
	margin-bottom: 20px;
	text-align: center;
}

/* 整個 form 水平排列 */
form {
	display: flex;
	justify-content: center;
	align-items: center;
	gap: 10px;
	flex-wrap: wrap;
}

/* label 不要太寬 */
.col-form-label {
	width: auto;
	margin-right: 10px;
}

/* input/select 大小 */
.form-select, .form-control {
	width: 250px;
}

/* 避免 bootstrap col-sm-10 撐滿 */
.col-sm-10 {
	width: auto;
}

form {
	display: flex;
	align-items: center;
	justify-content: center;
	gap: 10px;
	flex-wrap: nowrap;
}

.query-form {
	display: flex;
	align-items: center;
	justify-content: center;
	gap: 20px;
	margin-bottom: 30px;
}

/* 左邊文字區 */
.query-label {
	width: 220px;
	text-align: left;
}

/* 中間 input/select 區 */
.query-input {
	width: 350px !important;
}

/* 右邊 button 區 */
.query-btn {
	width: 100px;
}
</style>
</head>
<body bgcolor='white'>



	<div class="card text-center">
		<div class="card-header">
			<p>
				Webond Notification<BR> 資料查詢
			</p>
		</div>
		<div class="card-body">


			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red" class="card-text">請修正以下錯誤：</font>

				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>

			</c:if>

			<ul>



				<li><jsp:useBean id="notificationSvc" scope="page"
						class="com.webond.notification.model.NotificationService" />
					<FORM METHOD="post" ACTION="notification.do"
						class="d-flex justify-content-center align-items-center gap-2 query-form">

						<label class="col-form-label mb-0 query-label"> 輸入通知編號(如1)： </label> 
						<input type="text" name="notification_id"class="form-control w-auto query-input"> 
						<input type="hidden" name="action" value="getOne_For_Display"> 
						<input type="submit" value="送出" class="btn btn-primary query-btn">

					</FORM>
				</li>


				<li>
					<FORM METHOD="post" ACTION="notification.do"
						class="d-flex justify-content-center align-items-center gap-2 query-form">

						<label class="col-form-label mb-0 query-label"> 選擇通知編號: </label> 
						<select size="1" name="notification_id" class="form-select query-input">

							<c:forEach var="notificationVO" items="${notificationSvc.all}">
								<option value="${notificationVO.notificationId}">
									${notificationVO.notificationId}
								</option>
							</c:forEach>

						</select> 
						<input type="hidden" name="action" value="getOne_For_Display">
						<input type="submit" value="送出" class="btn btn-primary query-btn">
					</FORM>
				</li>

				<li>
					<a href='notification_add.jsp'
					class="btn  btn-outline-primary query-input">新增通知</a><br>
				</li>
				<li>
					<a href='notification_list_all.jsp'
					class="btn  btn-outline-primary query-input">查看所有通知</a><br> <br>
				</li>
			</ul>

		</div>

		<div class="card-footer">
			<br>
			<br>
			<br>
		</div>
	</div>
</body>
</html>