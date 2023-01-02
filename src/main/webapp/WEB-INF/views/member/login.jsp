<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
    <div class="container">
        <form action="/member/login" method="post">
            <input type="text" class="text-field" id="user_email" placeholder="이메일을 입력해주세요.">
            <input type="password" class="text-field" id="user_pw" placeholder="비밀번호를 입력해주세요.">
            <button type="submit">로그인</button>
        </form>
        <a href="/member/signup">회원가입</a>
    </div>
</body>
</html>