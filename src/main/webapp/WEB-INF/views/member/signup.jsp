<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/signup.css">

	
    <div class="wrap-sign">
	<div class="box-sign">
		<form action="/member/signup" class="wrap-input" method="post">
			<img src="/resources/source/logo2.png" class="logoImg"><br>
			<input type="email" class="input" name="user_email" placeholder="Email"><br><br>
			<input type="password" class="input" name="user_pw" placeholder="Password"><br><br>
			<input type="password" class="input" name="user_pwCheck" placeholder="Reconfirm Password"><br><br>
			<input type="text" class="input" name="user_name" placeholder="Name"><br><br>
			<input type="text" class="input" name="user_birth" placeholder="Birth"><br><br>
			<input type="text" class="input" name="user_phone" placeholder="Phone Number"><br><br>
			<input type="text" class="input" name="user_nick" placeholder="Nick Name"><br><br>
			<input type="button" class="input signupBtn" value="Welcome!"><br>
		</form>
	</div>
</div>
    
    <jsp:include page="../include/footer2.jsp"></jsp:include>
