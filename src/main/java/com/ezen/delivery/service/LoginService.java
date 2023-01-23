package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.LoginVO;

public interface LoginService {

	int loginDate(String user_id);

	int logoutDate(String user_id);

	List<LoginVO> getLoginList();

}
