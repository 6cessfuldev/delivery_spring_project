package com.ezen.delivery.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.delivery.domain.BasketDTO;
import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.service.BasketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/basket/*")
public class BasketController {

	@Inject
	private BasketService bsv;
	
	//등록성공 : 1 / 이미 데이터 있음 : 2 / 로그인 필요 : 3
	@PostMapping("/add") 
	@ResponseBody
	public String addBasketPOST(BasketDTO basket, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserVO uvo = (UserVO)session.getAttribute("user");
		if(uvo == null) {
			return "3";
		}
		
		return bsv.addBasket(basket)+"";
	}
	
}
