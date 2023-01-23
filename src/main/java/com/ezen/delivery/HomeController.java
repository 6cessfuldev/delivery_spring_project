package com.ezen.delivery;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.delivery.security.oauth2.PrincipalDetails;
import com.ezen.delivery.service.BasketService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Inject
	private BasketService bsv;

	@RequestMapping(value = {"/index","/"}, method = RequestMethod.GET)
	public String index(Authentication authentication, Model model) {
		
		int basket_count = 0;
		if(authentication != null) {
			PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
			String user_id = principalDetails.getUsername();
			basket_count = bsv.getCount(user_id);
		}
		model.addAttribute("basket_count", basket_count);
		
		return "index";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String testList() {
		return "/diner/list";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String testDetail() {
		return "/diner/detail";
	}
	
	
}
