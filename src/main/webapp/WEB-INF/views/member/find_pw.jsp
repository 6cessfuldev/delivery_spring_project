<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/resources/css/find_pw.css">

	 <div class="pw-wrap">
        <form action="" class="form-field">
            <input type="text" class="email-input" placeholder="먹어요에 가입한 이메일 주소 입력">
			<ul>
				<li class="pw-msg">
					입력하신 이메일 주소로 비밀번호 재설정 메일이 발송됩니다.
				</li>
				<li class="pw-msg">
					소셜 계정 회원은 비밀번호 찾기가 불가능합니다.
				</li>
			</ul>
            <button type="submit" class="emailBtn">이메일 발송</button>
        </form>
    </div>

<jsp:include page="../include/footer2.jsp"></jsp:include>