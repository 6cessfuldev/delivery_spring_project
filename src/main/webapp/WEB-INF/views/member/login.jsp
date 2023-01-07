<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>먹어요</title>

<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>

<link type="text/css" rel="stylesheet" href="/resources/css/login.css">


<jsp:include page="../include/header.jsp"></jsp:include>

   <div id="login_wrapper">
        <div id="login_content">
            <img src="/resources/source/logo2.png" id="login_logo">
            <div id="login_box">
        <form action="/member/login" method="post">
            <input type="text" id="login_input1" name="user_id" placeholder="아이디 입력(필수)"><br>
            <input type="password" id="login_input2" name="user_pw" placeholder="비밀번호 입력(필수)"><br><br>
            <input type="checkbox" id="login_check">
            <label for="login_check" id="login_checkLabel"><span>자동 로그인</span></label>
            <div id="login_aBox">
            <a href="/member/find_id" id="login_a">아이디 찾기  |</a>
            <a href="/member/find_pw" id="login_a">비밀번호 찾기  |</a>
            <a href="/member/signup" id="login_a">회원가입</a><br>
            </div>
            <br><br>
            <button type="submit" id="login_button">로그인</button>
        </form>
            </div>
            <br>
            <%
    String clientId = "BwPXQd2HaNZ5eWMSnF7z";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://localhost:8089/member/callback", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
         + "&client_id=" + clientId
         + "&redirect_uri=" + redirectURI
         + "&state=" + state;
    session.setAttribute("state", state);
 %>
  <a href="<%=apiURL%>"><img  width="250" height="55" src="/resources/source/btnW.png"/></a>
	       
            <br><br><br>
            <img src="/resources/source/event.gif" id="login_img">
             <br><br><br><br>
        </div>
    </div>
    
<jsp:include page="../include/footer2.jsp"></jsp:include>

<script type="text/javascript">
	const msg = '<c:out value="${msg}"/>';
	console.log(msg);
	if(msg === "0"){
	    alert("아이디 또는 비밀번호를 확인해주세요.");
	}
</script>
