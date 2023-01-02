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
        <input type="email" id="user_email" placeholder="이메일을 입력해주세요.">
        <input type="password" id="user_pw" placeholder="비밀번호를 입력해주세요.">
        <input type="text" id="user_name" placeholder="이름을 입력해주세요.">
        <input type="text" id="user_phone" placeholder="핸드폰 번호를 입력해주세요.">
        <input type="text" id="user_birth" placeholder="생년월일을 입력해주세요.">
        <input type="text" id="user_nick" placeholder="닉네임을 입력해주세요.">
        <button type="submit">Welcome</button>
    </form>
    
    <jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>