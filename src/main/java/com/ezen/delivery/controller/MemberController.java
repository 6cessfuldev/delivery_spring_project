package com.ezen.delivery.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {
		
	@Inject
	private UserService usv;

	@GetMapping("/signup")
	public void signUpGet() {}
	
	@PostMapping("/signup")
	public String signUpPost(Model model, UserVO uvo) {
		boolean isUp = usv.signUp(uvo);
		if(isUp) {
			return "/member/login";
		} else {
			model.addAttribute("msg", "0");
			return "/member/signup";
		}
	}
		
		

}
