package com.ezen.delivery.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.ezen.delivery.service.LoginService;
import com.ezen.delivery.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

	@Inject
	private UserService usv;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		log.info("=========login Fail==========");
		
		String user_id = request.getParameter("user_id");
		log.info("user_id : " +user_id);
		
		int isOk = usv.loginFailCnt(user_id);
		log.info(">>> add fail Count " + (isOk > 0 ? "Ok" : "Fail"));
		request.setAttribute("msg", "0");
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
		

	}
}
