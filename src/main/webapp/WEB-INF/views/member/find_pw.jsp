<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>먹어요</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/find_pw.css">

<div class="pw-wrap">
	<form action="/member/find_pw" class="form-field" method="post">
		<div id="box">
			<input type="text" class="email-input" name="user_email" id="user_email"
				placeholder="먹어요에 가입한 이메일 주소 입력">
		</div>
		<ul>
			<li class="pw-msg">
				입력하신 이메일 주소로 인증번호 메일이 발송됩니다.
			</li>
			<li class="pw-msg">
				소셜 계정 회원은 비밀번호 찾기가 불가능합니다.
			</li>
		</ul>
		<button type="button" class="emailBtn" onclick="add_checkBox()">이메일 발송</button>
	</form>
</div>

<jsp:include page="../include/footer2.jsp"></jsp:include>

<script src="/resources/js/find_pw.js"></script>