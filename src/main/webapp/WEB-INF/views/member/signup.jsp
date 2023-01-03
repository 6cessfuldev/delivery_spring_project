<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/signup.css">

	
<div class="wrap-sign">
	<div class="box-sign">
		<form action="/member/signup" class="wrap-input" method="post">
			<img src="/resources/source/logo2.png" class="logoImg"><br>
			<div id="box">
				<input type="email" class="input" name="user_email" id="user_email" placeholder="Email"><br><br>
				<button type="button" class="eBtn"  onclick="add_textbox()">인증</button>
			</div>
			<input type="password" class="input" name="user_pw" id="user_pw" placeholder="Password"><br><br>
			<input type="password" class="input" name="user_pwCheck" id="user_pwCheck" placeholder="Reconfirm Password"><br><br>
			<input type="text" class="input" name="user_name" id="user_name" placeholder="Name"><br><br>
			<input type="text" class="input" name="user_birth" id="user_birth" placeholder="Birth"><br><br>
			<input type="text" class="input" name="user_phone" id="user_phone" placeholder="Phone Number"><br><br>
			<input type="text" class="input" name="user_nick" id="user_nick" placeholder="Nick Name"><br><br>
			<input type="button" class="input signupBtn" value="Welcome!"><br>
		</form>
	</div>
</div>
    
    <jsp:include page="../include/footer2.jsp"></jsp:include>

	<script src="/resources/js/signup.js"></script>
