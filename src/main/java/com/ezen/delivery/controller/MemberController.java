package com.ezen.delivery.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		log.info(uvo.toString());
		boolean isUp = usv.signUp(uvo);
		if(isUp) {
			return "/member/login";
		} else {
			model.addAttribute("msg", "0");
			return "/member/signup";
		}
	}
	
	@GetMapping("/login")
	public void loginGet() {}
	
	@PostMapping("/login")
	public String loginPost(Model model, String user_email, String user_pw, HttpServletRequest req) {
		log.info(">>> user_email : " + user_email + " >>> user_pw : " + user_pw);
		UserVO isUser = usv.isUser(user_email, user_pw);
		
		if(isUser != null) {
			HttpSession session = req.getSession();
			session.setAttribute("ses", isUser);
			
			model.addAttribute("user", isUser);
			model.addAttribute("msg", "1");
			return "redirect:home";
		} else {
			model.addAttribute("msg", "0");
			return "/member/login";
		}
	}
	
	
	@GetMapping("/find_id")
	public void findIdGet() {}
	
	@GetMapping("/find_pw")
	public void findPwGet() {}
	
	@GetMapping("/order")
	public void orderGet() {}
		

}
