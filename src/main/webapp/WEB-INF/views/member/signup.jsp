<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>먹어요</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/signup.css">

	
<div class="wrap-sign">
	<div class="box-sign">
		<form action="/member/signup" class="wrap-input" method="post">
			<img src="/resources/source/logo2.png" class="logoImg"><br>
			<div id="box">
				<input type="email" class="input email_input" name="user_email" id="user_email"
				placeholder="Email"><br><br>
				<button type="button" class="eBtn" onclick="add_checkBox()">전송</button>
			</div>
			<div id="id-box">
				<input type="text" class="input" name="user_id" id="user_id" placeholder="ID"><br><br>
				<button type="button" class="dupBtn" onclick="idCheck()">중복</button>
				<div id="id_div"></div>
			</div>
			<input type="password" class="input" name="user_pw" id="user_pw" placeholder="Password"><br><br>
			<input type="password" class="input" name="user_pwCheck" id="user_pwCheck"
				placeholder="Reconfirm Password"><br><br>
			<input type="text" class="input" name="user_name" id="user_name" placeholder="Name"><br><br>
			<input type="text" class="input" name="user_birth" id="user_birth" placeholder="Birth"><br><br>
			<input type="text" class="input" name="user_phone" id="user_phone" placeholder="Phone Number (-제외)"><br><br>
			<input type="button" class="input signupBtn" value="Welcome!"><br>
		</form>
	</div>
</div>
    
    <jsp:include page="../include/footer2.jsp"></jsp:include>

	<script src="/resources/js/signup.js"></script>
