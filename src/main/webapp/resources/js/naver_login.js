

  	var naver_id_login = new naver_id_login("BwPXQd2HaNZ5eWMSnF7z", "http://localhost:8089/member/callback");
  	var state = naver_id_login.getUniqState();
  	naver_id_login.setButton("white", 2,40);
  	naver_id_login.setDomain("http://localhost:8089/member/login");
  	naver_id_login.setState(state);
  	naver_id_login.setPopup();
  	naver_id_login.init_naver_id_login();