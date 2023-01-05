<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>먹어요</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/find_id.css">

<div class="join-wrap">
    <form action="/member/find_id" method="post">
        <div id="box">
            <li class="text-wrap">
                <input type="text" class="text-field t1 email-input" id="user_email" placeholder="(필수)이메일 주소 입력">
                <button type="button" class="cerBtn">전송</button>
            </li>
        </div>
        <li class="text-wrap">
            <input type="text" class="text-field t2 mail_check_input" placeholder="인증번호 입력">
            <div class='mail_check_button'>
                <input type='button' class='cBtn' value='인증'>
            </div>
            <div class='clearfix'></div>
            <span id="mail_check_input_box_warn"></span>
        </li>
        <p class="msg">인증번호가 도착하지 않았을 경우 '인증'버튼을 다시 눌러주세요.</p>
        <div id="id_hint"></div>
        <button type="button" class="findBtn">아이디 찾기</button>
    </form>
</div>

<jsp:include page="../include/footer2.jsp"></jsp:include>

<script src="/resources/js/find_id.js"></script>