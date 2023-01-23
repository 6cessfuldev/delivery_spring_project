package com.ezen.delivery.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.ezen.delivery.security.oauth2.PrincipalDetails;
import com.ezen.delivery.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Inject
	private LoginService lsv;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		String user_id = principalDetails.getUsername();
	
		int isOk = lsv.logoutDate(user_id);
		log.info(">>> update logout date " + (isOk > 0 ? "Ok" : "Fail"));
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);

	}

}
