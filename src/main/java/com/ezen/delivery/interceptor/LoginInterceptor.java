package com.ezen.delivery.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//
//		log.info("LoginInterceptor preHandle worked");
//		
//		//로그인 시 이전 세션 정보가 남아 있는 경우를 배제하기 위해
//		HttpSession session = request.getSession();
//		session.invalidate();
//		
//		return true;
//	}

	
	
}
