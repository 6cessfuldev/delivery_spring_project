<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>


<script type="text/javascript">
  var naver_id_login = new naver_id_login("BwPXQd2HaNZ5eWMSnF7z", "http://localhost:8089/member/callback");
//접근 토큰 값 출력
  alert(naver_id_login.oauthParams.access_token);
  <%
// 네이버 사용자 프로필 조회
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
//네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  function naverSignInCallback() {
    alert(naver_id_login.getProfileData('email'));
    alert(naver_id_login.getProfileData('name'));
  }
</script>

<%
	request.setAttribute("AccessToken", naver_id_login.oauthParams.access_token);
	request.setAttribute("RefreshToken", new Integer(num1 - num2));
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("FourRulesResult.jsp");
	dispatcher.forward(request, response);
%>
