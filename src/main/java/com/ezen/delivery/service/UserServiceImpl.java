package com.ezen.delivery.service;

import java.util.List;

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
		
		if(uvo.getUser_id() == null || uvo.getUser_id().length() == 0) {
			return false;
		}

		UserVO tmpUser = udao.getUser(uvo.getUser_id());

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
	public UserVO isUser(String user_id, String user_pw) {
		UserVO uvo = udao.getUser(user_id);
		
		if(uvo == null) {return null;}
		
		if(passwordEncoder.matches(user_pw, uvo.getUser_pw())) {
			return uvo;
		} else {
			return null;			
		}
	}



	@Override
	public List<String> getAllId() {
		
		return udao.selectAllId();
	}

	@Override
	public UserVO getUserByID(String user_id) {
		
		return udao.getUser(user_id);
	}

	@Override
	public int isExist(String user_id) {
	
		return udao.selectCntById(user_id);
	}

	@Override
	public UserVO getId(String user_email) {
		
		return udao.findIdByEmail(user_email);
	}

	@Override
	public int updatePw(String getEmail, String new_pw) {
		
		String encodeNewPw = passwordEncoder.encode(new_pw);
		new_pw = encodeNewPw;
		
		return udao.updatePw(getEmail, new_pw);
	}

	@Override
	public UserVO getUserDetail(String user_id) {
		
		return udao.selectUserOne(user_id);
	}

	@Override
	public int modifyUserInfo(String user_id, String new_pw, String new_phone) {
		
		String encodeNewPw = passwordEncoder.encode(new_pw);
		new_pw = encodeNewPw;
		
		return udao.updateUser(user_id, new_pw, new_phone);
	}

	@Override
	public int emailExist(String user_email) {
		
		return udao.selectCntByEmail(user_email);
	}

	
	

}
