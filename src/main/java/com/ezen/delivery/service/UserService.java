package com.ezen.delivery.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ezen.delivery.domain.UserVO;

public interface UserService {

	boolean signUp(UserVO uvo);

	UserVO isUser(String user_email, String user_pw);

	int isExist(String user_id);

	List<String> getAllId();

	UserVO getUserByID(String user_id);

	UserVO getId(String user_email);

	int updatePw(String getEmail, String new_pw);

	UserVO getUserDetail(String user_id);

	int modifyUserInfo(String user_id, String new_pw, String new_phone, HttpSession session);

	int emailExist(String user_email);

	int removeUserInfo(String user_id);

	boolean naverSignUp(UserVO naverUser);

	List<UserVO> getUserList();

	int modifyUserInfo(UserVO uvo);

	int loginFailCnt(String user_id);
	
	





}
