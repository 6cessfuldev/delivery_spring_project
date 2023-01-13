<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>먹어요</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/signup.css">

	
<div class="wrap-sign">
	<div class="box-sign">
		<form action="/admin/user/insert" class="wrap-input">
			<img src="/resources/source/logo2.png" class="logoImg"><br>
				<input type="email" class="input email_input" name="user_email" id="user_email"
					placeholder="Email"><br><br>
			<div id="id-box">
					<input type="text" class="input" name="user_id" id="user_id" placeholder="ID"><br><br>
			</div>
			<input type="text" class="input" name="user_pw" id="user_pw" placeholder="Password"><br><br>
			<input type="text" class="input" name="user_pwCheck" id="user_pwCheck" placeholder="Reconfrim Password"><br><br>
			<input type="text" class="input" name="user_name" id="user_name" placeholder="Name"><br><br>
			<input type="text" class="input" name="user_birth" id="user_birth" placeholder="Birth (yyMMdd)"><br><br>
			<input type="text" class="input" name="user_phone" id="user_phone"
				placeholder="Phone Number (-포함)"><br><br>
			<input type="button" class="input signupBtn" value="회원등록"><br>
		</form>
	</div>
</div>
    
    <jsp:include page="../include/footer.jsp"></jsp:include>

	<script src="/resources/js/signup.js"></script>
