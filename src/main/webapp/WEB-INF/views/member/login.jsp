<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/login.css">
	<!-- <main> -->
   <div id="login_wrapper">
        <div id="login_content">
            <img src="/resources/source/logo2.png" id="login_logo">
            <div id="login_box">
        <form action="">
            <input type="text" id="login_input1" placeholder="이메일 주소 입력(필수)"><br>
            <input type="text" id="login_input2" placeholder="비밀번호 입력(필수)"><br><br>
            <input type="checkbox" id="login_check">
            <label for="login_check" id="login_checkLabel"><span>자동 로그인</span></label>
            <div id="login_aBox">
            <a href="/member/find_id" id="login_a">아이디 찾기  |</a>
            <a href="/member/find_pw" id="login_a">비밀번호 찾기  |</a>
            <a href="/member/signup" id="login_a">회원가입</a><br>
            </div>
            <br><br>
            <button id="login_button">로그인</button>
        </form>
            </div>
            <br>
            <button id="login_buttonA1"><img src="/resources/source/naver.jpg" id="login_icon">네이버로 로그인</button>
            <button id="login_buttonA2"><img src="/resources/source/kakao.jpg" id="login_icon">카카오로 로그인</button>
            <br><br><br>
            <img src="/resources/source/event.gif" id="login_img">
             <br><br><br><br>
        </div>
    </div>
<!-- </main> -->
<jsp:include page="../include/footer2.jsp"></jsp:include>
