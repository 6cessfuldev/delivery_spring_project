package com.ezen.delivery.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO udao;
	@Inject
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public boolean signUp(UserVO uvo) {
		log.info(">>> signup check2");
		
		UserVO tmpUser = udao.getUser(uvo.getUser_email());
		
		if(tmpUser == null) {
			return false;
		}
		
		if(uvo.getUser_email() == null || uvo.getUser_email().length() == 0) {
			return false;
		}
		
		String pw = uvo.getUser_pw();
		String encodePw = passwordEncoder.encode(pw);
		uvo.setUser_pw(encodePw);
		
		int isOk = udao.insertUser(uvo);
		
		return true;
	}

}
