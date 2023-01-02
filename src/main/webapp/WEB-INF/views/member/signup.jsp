<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

	<jsp:include page="../include/header.jsp"></jsp:include>
	
    <form action="/member/signup" method="post">
        <input type="email" id="user_email" placeholder="이메일 입력">
        <input type="password" id="user_pw" placeholder="비밀번호 입력">
        <input type="text" id="user_name" placeholder="이름 입력">
        <input type="text" id="user_phone" placeholder="핸드폰 번호 입력">
        <input type="text" id="user_birth" placeholder="생년월일 입력">
        <input type="text" id="user_nick" placeholder="닉네임 입력">
        <button type="submit">Welcome</button>
    </form>
    
    <jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>