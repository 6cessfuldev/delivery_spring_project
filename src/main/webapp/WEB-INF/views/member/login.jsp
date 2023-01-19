<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>먹어요</title>

<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.security.SecureRandom"%>
<%@ page import="java.math.BigInteger"%>

<link type="text/css" rel="stylesheet" href="/resources/css/login.css">
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<jsp:include page="../include/header.jsp"></jsp:include>

<div id="login_wrapper">
	<div id="login_content">
		<img src="/resources/source/logo2.png" id="login_logo">
		<div id="login_box">
			<form action="/member/login" method="post">
				<input type="text" id="login_input1" name="user_id"
					placeholder="아이디 입력(필수)"><br> <input type="password"
					id="login_input2" name="user_pw" placeholder="비밀번호 입력(필수)"><br>
				<br> <input type="checkbox" id="login_check"> <label
					for="login_check" id="login_checkLabel"><span>자동 로그인</span></label>
				<div id="login_aBox">
					<a href="/member/find_id" id="login_a">아이디 찾기 |</a> <a
						href="/member/find_pw" id="login_a">비밀번호 찾기 |</a> <a
						href="/member/signup" id="login_a">회원가입</a><br>
				</div>
				<br> <br>
				<button type="submit" id="login_button">로그인</button>
			</form>
		</div>
		<br>
		<a href="/oauth2/authorization/google">구글 로그인</a>
		<a href="/oauth2/authorization/naver">네이버 로그인</a>
		<!-- 네이버 로그인 버튼 노출 영역 -->
		<div id="naver_id_login"></div>
		<!-- //네이버 로그인 버튼 노출 영역 -->
		<script type="text/javascript">
			var naver_id_login = new naver_id_login("BwPXQd2HaNZ5eWMSnF7z",
					"http://localhost:8089/member/callback");
			var state = naver_id_login.getUniqState();
			naver_id_login.setButton("white", 3, 50);
			naver_id_login.setDomain("http://localhost:8089/member/login");
			naver_id_login.setState(state);
			naver_id_login.setPopup();
			naver_id_login.init_naver_id_login();

			function gotoMain() {
				location.href = "/index?user.user_id=${user.user_id}";
			}
		</script>


		<br> <br> <br> <img src="/resources/source/event.gif"
			id="login_img"> <br> <br> <br> <br>
	</div>
</div>

<jsp:include page="../include/footer2.jsp"></jsp:include>


<script type="text/javascript">
	const msg = '<c:out value="${msg}"/>';
	console.log(msg);
	if (msg === "0") {
		alert("아이디 또는 비밀번호를 확인해주세요.");
	}
</script>
