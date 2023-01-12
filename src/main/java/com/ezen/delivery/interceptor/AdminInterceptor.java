package com.ezen.delivery.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ezen.delivery.domain.UserVO;

public class AdminInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		
		UserVO uvo = (UserVO)session.getAttribute("user");
		
		if(uvo == null || uvo.getUser_isAdmin()!=1) {
			response.sendRedirect("/main");
			return false;
		}
		
		return true;
	}

	
	
}
