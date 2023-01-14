<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/resources/js/naveridlogin_js_sdk_2.0.2.js"></script>
</head>
<body>
<form id="form" action="/member/naverLogin" method="post">
<input id="accessToken" type="text" name="accessToken" value="" hidden>
<input id="state" type="text" name="state" value="" hidden>
</form>

<script type="text/javascript">
  var naver_id_login = new naver_id_login("BwPXQd2HaNZ5eWMSnF7z", "http://localhost:8089/member/callback");
 

  naver_id_login.get_naver_userprofile("naverSignInCallback()");

  function naverSignInCallback() {

    var accessToken = document.getElementById('accessToken');
    var state = document.getElementById('state');
    
    accessToken.value = naver_id_login.oauthParams.access_token;
    state.value = naver_id_login.oauthParams.state;
    document.getElementById("form").submit();  
  
  }
  
</script>
</body>
</html>
