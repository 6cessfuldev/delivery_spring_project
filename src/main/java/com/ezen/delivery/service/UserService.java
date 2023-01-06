package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.UserVO;

public interface UserService {

	boolean signUp(UserVO uvo);

	UserVO isUser(String user_email, String user_pw);

	int isExist(String user_id);

	List<String> getAllId();

	UserVO getUserByID(String user_id);

	UserVO getId(String user_email);

	int updatePw(String getEmail, String new_pw);


}
