<!doctype html>
<html lang="ko">
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/resources/js/naveridlogin_js_sdk_2.0.2.js"></script>
</head>
<body>

<form id="form" action="/member/naverLogin" method="post">
<input id="accessToken" type="text" name="accessToken" value="" >
<input id="state" type="text" name="state" value="" >
</form>

<script type="text/javascript">
  var naver_id_login = new naver_id_login("BwPXQd2HaNZ5eWMSnF7z", "http://localhost:8089/member/callback");
 
  // ë¤ì´ë² ì¬ì©ì íë¡í ì¡°í
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  // ë¤ì´ë² ì¬ì©ì íë¡í ì¡°í ì´í íë¡í ì ë³´ë¥¼ ì²ë¦¬í  callback function
  function naverSignInCallback() {
	// ì ê·¼ í í° ê°
    var accessToken = document.getElementById('accessToken');
    var state = document.getElementById('state');
    
    accessToken.value = naver_id_login.oauthParams.access_token;
    state.value = naver_id_login.oauthParams.state;
    document.getElementById("form").submit();  
  
  }
  

</script>
</body>
</html>