package com.ezen.delivery.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ezen.delivery.domain.UserVO;

public class OrderInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		UserVO uvo = (UserVO)session.getAttribute("user");
		
		if(uvo == null) {
			response.sendRedirect("/member/login");
			return false;
		}else {
			return true;
		}
	}

	
	
}
