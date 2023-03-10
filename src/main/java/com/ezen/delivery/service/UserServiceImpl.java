package com.ezen.delivery.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.repository.UserDAO;
import com.ezen.delivery.security.oauth2.PrincipalDetails;

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
			log.info("존재하는 아이디 입니다.");
			return false;
		}
		
		String pw = uvo.getUser_pw();
		String encodePw = passwordEncoder.encode(pw);
		uvo.setUser_pw(encodePw);
	
		return udao.insertUser(uvo);
	}
	
	@Override
	public boolean naverSignUp(UserVO naverUser) {
		log.info(">>> naverSignUp check2");
		return udao.insertNaverUser(naverUser);
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
		
		// 비밀번호 암호화
		String encodeNewPw = passwordEncoder.encode(new_pw);
		new_pw = encodeNewPw;
		
		// 기존 비밀번호와 비교
		UserVO uvo = udao.getUserPw(getEmail);
		boolean equal = passwordEncoder.matches(new_pw, uvo.getUser_pw());
		
		if(!equal) {
			return udao.updatePw(getEmail, new_pw);			
		} else {
			return 0;
		}
	}

	@Override
	public UserVO getUserDetail(String user_id) {
		
		return udao.selectUserOne(user_id);
	}

	@Override
	public int modifyUserInfo(String user_id, String new_pw, String new_phone, HttpSession session) {
		
		// 비밀번호 암호화
		String encodeNewPw = passwordEncoder.encode(new_pw);
		new_pw = encodeNewPw;
		
		int isUp = udao.updateUser(user_id, new_pw, new_phone);
		UserVO uvo = udao.getUser(user_id);
		//세션 변경
		if(isUp>0) {
			SecurityContextHolder.clearContext();
		    PrincipalDetails updateUserDetails = new PrincipalDetails(uvo);
			Authentication newAuthentication = new UsernamePasswordAuthenticationToken(updateUserDetails, null, updateUserDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(newAuthentication);
			session.setAttribute("SPRING_SECURITY_CONTEXT", newAuthentication);
		}
		
		return isUp; 
	}

	@Override
	public int emailExist(String user_email) {
		
		return udao.selectCntByEmail(user_email);
	}

	@Override
	public int removeUserInfo(String user_id) {
		
		return udao.deleteUser(user_id);
	}

	@Override
	public List<UserVO> getUserList() {
		return udao.selectList();
	}

	// 관리자페이지 회원 정보 수정
	@Override
	public int modifyUserInfo(UserVO uvo) {
		
		String encodeNewPw = passwordEncoder.encode(uvo.getUser_pw());
		uvo.setUser_pw(encodeNewPw);
		
		return udao.updateUserFromAdmin(uvo);
	}
	
	@Override
	public int loginFailCnt(String user_id) {
		return udao.updateLoginFailCnt(user_id);
	}
	

}
