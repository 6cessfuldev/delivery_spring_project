<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>먹어요</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/update_pw.css">

<div class="reset-wrap">
	<form class="form-box" action="/member/reset_pw" method="post">
		<img src="/resources/source/logo2.png" class="logoImg"><br>
		<input type="password" class="text-field" id="new_pw" placeholder="새로운 비밀번호 입력"><br><br>
		<input type="password" class="text-field" id="new_pwCheck" placeholder="비밀번호 재확인"><br><br>
		<button type="button" class="changeBtn">Update My Password</button>
	</form>
</div>

<jsp:include page="../include/footer2.jsp"></jsp:include>

<script src="/resources/js/update_pw.js"></script>