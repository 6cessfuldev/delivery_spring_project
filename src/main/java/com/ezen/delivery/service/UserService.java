package com.ezen.delivery.service;

import com.ezen.delivery.domain.UserVO;

public interface UserService {

	boolean signUp(UserVO uvo);

	UserVO isUser(String user_email, String user_pw);

}
