package com.ezen.delivery.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.delivery.security.oauth2.PrincipalDetails;
import com.ezen.delivery.service.BasketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasketInterceptor implements HandlerInterceptor {

	@Inject
	private BasketService bsv;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null && authentication.getPrincipal()!="anonymousUser") {
			PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
			String user_id = principalDetails.getUsername();
			int basketCount = bsv.getCount(user_id);
			request.setAttribute("basket_count", basketCount);
		}
	
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	
	
}
