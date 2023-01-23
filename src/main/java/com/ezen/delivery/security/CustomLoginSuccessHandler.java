package com.ezen.delivery.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ezen.delivery.security.oauth2.PrincipalDetails;
import com.ezen.delivery.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Inject
	private LoginService lsv;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		log.info("=========login Success==========");
		
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		String user_id = principalDetails.getUsername();
		
		int isOk = lsv.loginDate(user_id);
		log.info(">>> update login date " + (isOk > 0 ? "Ok" : "Fail"));
		
		List<String> roleNames = new ArrayList<String>();
		
		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		log.info("ROLE NAMES : " + roleNames);
		
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin/");
			return;
		}
		
		response.sendRedirect("/");
	}

}
