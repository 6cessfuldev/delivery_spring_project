package com.ezen.delivery.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	PasswordEncoder passwordEncoder;

	@Override
	public boolean signUp(UserVO uvo) {
		log.info(">>> signup check2");
		
		if(uvo.getUser_email() == null || uvo.getUser_email().length() == 0) {
			return false;
		}

		UserVO tmpUser = udao.getUser(uvo.getUser_email());

		if(tmpUser != null) {
			log.info("존재하는 이메일 입니다.");
			return false;
		}
		
		String pw = uvo.getUser_pw();
		String encodePw = passwordEncoder.encode(pw);
		uvo.setUser_pw(encodePw);
		
		int isOk = udao.insertUser(uvo);
		return true;
	}

	@Override
	public UserVO isUser(String user_email, String user_pw) {
		UserVO uvo = udao.getUser(user_email);
		
		if(uvo == null) {return null;}
		
		if(passwordEncoder.matches(user_pw, uvo.getUser_pw())) {
			return uvo;
		} else {
			return null;			
		}
	}

}
