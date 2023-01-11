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
	
	@PostMapping("/add") 
	@ResponseBody
	public String addBasketPOST(BasketDTO basket, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserVO uvo = (UserVO)session.getAttribute("user");
		if(uvo == null) {
			return "5";
		}
		
		return bsv.addBasket(basket)+"";
	}
	
}
