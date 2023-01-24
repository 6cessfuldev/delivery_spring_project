package com.ezen.delivery.repository;

import java.util.List;

import com.ezen.delivery.domain.LoginVO;

public interface LoginDAO {

	int insertLoginDate(String user_id);

	int updateLogoutDate(String user_id);

	List<LoginVO> selectLoginList();


}
