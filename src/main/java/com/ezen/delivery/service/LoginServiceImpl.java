package com.ezen.delivery.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.LoginVO;
import com.ezen.delivery.repository.LoginDAO;

@Service
public class LoginServiceImpl implements LoginService {

	@Inject
	private LoginDAO ldao;
	
	@Override
	public int loginDate(String user_id) {
		return ldao.insertLoginDate(user_id);
	}
	
	@Override
	public int logoutDate(String user_id) {
		return ldao.updateLogoutDate(user_id);
	}

	@Override
	public List<LoginVO> getLoginList() {
		return ldao.selectLoginList();
	}

}
