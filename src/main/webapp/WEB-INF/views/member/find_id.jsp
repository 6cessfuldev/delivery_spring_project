<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/find_id.css">

	<div class="join-wrap">
        <form action="">
            <li class="text-wrap">
                <input type="text" class="text-field t1" placeholder="(필수)휴대폰 전화번호 입력(-제외)">
                <button type="button" class="cerBtn">인증</button>
            </li>
            <li class="text-wrap">
                <input type="text" class="text-field" placeholder="인증번호 입력">
            </li>
            <p class="msg">인증번호가 도착하지 않았을 경우 '인증'버튼을 다시 눌러주세요.</p>
            <button type="submit" class="findBtn">아이디 찾기</button>
        </form>
    </div>

<jsp:include page="../include/footer2.jsp"></jsp:include>