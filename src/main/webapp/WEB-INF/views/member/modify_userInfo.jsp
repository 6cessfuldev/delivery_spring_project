<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>먹어요</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/userInfo.css">

<div class="category">
	<ul class="nav justify-content-center">
		<li class="nav-item">
			<div class="category-btn" onclick='location.href ="/index";'>주문/결제 조회</div>
		</li>
		<li class="nav-item">
			<div class="category-btn" id="cate-ff">찜목록</div>
		</li>
		<li class="nav-item">
			<div class="category-btn" id="cate-hh">리뷰 관리</div>
		</li>
		<li class="nav-item">
			<div class="category-btn" id="cate-pp">적립금 내역</div>
		</li>
	</ul>
</div>

<div class="wrap-sign">
	<div class="box-sign">
        <form action="/member/detail_userInfo" class="wrap-input">
			<img src="/resources/source/logo2.png" class="logoImg"><br>
			<div id="box">
				<input type="email" class="input email_input" name="user_email" id="user_email"
					value="${user.user_email }" readonly="readonly"><br><br>
			</div>
			<div id="id-box">
				<input type="text" class="input" name="user_id" id="user_id" value="${user.user_id }" readonly="readonly"><br><br>
			</div>
			<input type="password" class="input" name="user_pw" id="user_pw" placeholder="Password"><br><br>
			<input type="password" class="input" name="user_pwCheck" id="user_pwCheck"
				placeholder="Reconfirm Password"><br><br>
			<input type="text" class="input" name="user_name" id="user_name" value="${user.user_name }" readonly="readonly"><br><br>
			<input type="text" class="input" name="user_birth" id="user_birth" value="${user.user_birth }" readonly="readonly"><br><br>
			<input type="text" class="input" name="user_phone" id="user_phone"
				value="${user.user_phone }" placeholder="Phone Number (-포함)"><br><br>
			<input type="button" class="updateBtn" value="Update My Information"><br>
        </form>
	</div>
</div>

<jsp:include page="../include/footer2.jsp"></jsp:include>

<script src="/resources/js/modify_userInfo.js"></script>