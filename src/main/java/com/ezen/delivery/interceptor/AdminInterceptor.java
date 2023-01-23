package com.ezen.delivery.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class AdminInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		/*
		 * HttpSession session = request.getSession();
		 * 
		 * UserVO uvo = (UserVO)session.getAttribute("user");
		 * 
		 * if(uvo == null || uvo.getUser_Role()!=Role.ROLE_USER) {
		 * response.sendRedirect("/main"); return false; }
		 */
		return true;
	}

	
	
}
