package com.ezen.delivery.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.delivery.domain.DibsVO;
import com.ezen.delivery.security.oauth2.PrincipalDetails;
import com.ezen.delivery.service.DibsService;

@Controller
@RequestMapping("/dibs/*")
public class DibsController {
	
	@Inject
	private DibsService dsv;
	
	@ResponseBody
	@GetMapping("/update/{diner_code}")
	public String update(@PathVariable("diner_code")int diner_code, Authentication authentication) {
		
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		String user_id = principalDetails.getUsername();
		
		DibsVO dvo = new DibsVO();
		dvo.setDiner_code(diner_code);
		dvo.setUser_id(user_id);
		
		int isUp = dsv.creatOrDelete(dvo);
		
		
		return isUp + "";
	}
	
	@GetMapping("/myDibsList")
	public String myDibsListGET(Model model) {
		
		
		return "member/myDibsList";
	}
	
}
