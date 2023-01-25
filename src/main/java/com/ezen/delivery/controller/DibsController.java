package com.ezen.delivery.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.delivery.domain.DibsVO;
import com.ezen.delivery.domain.DinerDTO;
import com.ezen.delivery.security.oauth2.PrincipalDetails;
import com.ezen.delivery.service.DibsService;
import com.ezen.delivery.service.DinerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/dibs/*")
public class DibsController {
	
	@Inject
	private DibsService disv;
	
	@Inject
	private DinerService dsv;
	
	@ResponseBody
	@GetMapping("/update/{diner_code}")
	public String update(@PathVariable("diner_code")int diner_code, Authentication authentication) {
		
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		String user_id = principalDetails.getUsername();
		
		DibsVO dvo = new DibsVO();
		dvo.setDiner_code(diner_code);
		dvo.setUser_id(user_id);
		
		int isUp = disv.creatOrDelete(dvo);
		
		return isUp + "";
	}
	
	@GetMapping("/myDibsList")
	public String myDibsListGET(Authentication authentication, Model model) {
		if(authentication == null) {
			return "/member/login";
		}
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		String user_id = principalDetails.getUser().getUser_id();
		log.info("user_id : " + user_id);
		
		List<DibsVO> dibsList = disv.dibsList(user_id);
		
		List<DinerDTO> ddtoList = new ArrayList<DinerDTO>();
		
		for(int i=0; i<dibsList.size(); i++) {
			
			int diner_code = dibsList.get(i).getDiner_code();
			DinerDTO ddto = dsv.getDiner(diner_code);
			
			ddtoList.add(ddto);
		}

		model.addAttribute("ddtoList", ddtoList);
		
		return "/member/myDibsList";
	}
	
	@GetMapping("/remove")
	public String removeDibs(@RequestParam(name="diner_code")int diner_code, Authentication authentication) {
		if(authentication==null) {
			return "/member/login"; 			
		}
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		disv.creatOrDelete(new DibsVO(principalDetails.getUsername(), diner_code));
		return "/diner/detail?diner_code="+diner_code;
	}
	
}
