<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>먹어요</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/userInfo.css">

<form action="/admin/user/update">
<div class="wrap-sign">
	<div class="box-sign">
			<img src="/resources/source/logo2.png" class="logoImg"><br>
			<div id="id-box">
				<input type="text" class="input" name="user_id" id="user_id" value="${user.user_id }" readonly="readonly"><br><br>
			</div>
			<div id="box">
				<input type="email" class="input email_input" name="user_email" id="user_email"
					value="${user.user_email }"><br><br>
			</div>
			<input type="text" class="input" name="user_pw" id="user_pw" value="${user.user_pw }"><br><br>
			<input type="text" class="input" name="user_name" id="user_name" value="${user.user_name }"><br><br>
			<input type="text" class="input" name="user_birth" id="user_birth" value="${user.user_birth }"><br><br>
			<input type="text" class="input" name="user_phone" id="user_phone"
				value="${user.user_phone }"><br><br>
			<div id="modBtn-box">
			<input type="submit" class="btn modBtn" value="수정완료"><br>
			</div>
	</div>
</div>
</form>

<jsp:include page="../include/footer.jsp"></jsp:include>

<script src="/resources/js/detail_userInfo.js"></script>