<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<title>먹어요</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/userInfo.css">

<jsp:include page="../include/myPageHeader.jsp"></jsp:include>

<div class="wrap-sign">
	
	<div class="box-sign">
			<img src="/resources/source/logo2.png" class="logoImg"><br>
			<div id="box">
				<input type="email" class="input email_input" name="user_email" id="user_email"
					value="${user.user_email }" readonly="readonly"><br><br>
			</div>
			<div id="id-box">
				<input type="text" class="input" name="user_id" id="user_id" value="${user.user_id }" readonly="readonly"><br><br>
			</div>
			<input type="password" class="input" name="user_pw" id="user_pw" value="**********" readonly="readonly"><br><br>
			<input type="password" class="input" name="user_pwCheck" id="user_pwCheck"
				value="**********" readonly="readonly"><br><br>
			<input type="text" class="input" name="user_name" id="user_name" value="${user.user_name }" readonly="readonly"><br><br>
			<input type="text" class="input" name="user_birth" id="user_birth" value="${user.user_birth }" readonly="readonly"><br><br>
			<input type="text" class="input" name="user_phone" id="user_phone"
				value="${user.user_phone }" readonly="readonly"><br><br>
			<div id="modBtn-box">
			<c:if test="${isOauth == 0 }">
				<input type="button" onclick="location.href='/member/modify_userInfo'" class="btn modBtn" value="정보수정"><br>
			</c:if>
			</div>
			<div id="delBtn-box">
				<input type="button" class="btn delBtn" value="회원탈퇴"><br>
			</div>
	</div>
</div>

<jsp:include page="../include/footer2.jsp"></jsp:include>
<script src="/resources/js/detail_userInfo.js"></script>
