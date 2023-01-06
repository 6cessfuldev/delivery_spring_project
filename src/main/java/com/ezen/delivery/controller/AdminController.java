package com.ezen.delivery.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.service.DinerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/*")
public class AdminController {

	@Inject
	private DinerService dsv;
	
	@GetMapping("/register")
	public void register() {}
	
	@GetMapping("/diner/insert")
	public String insert(DinerVO dvo) {
		
		dsv.register(dvo);
		log.info(dvo.toString());
		
		return "redirect:/admin/register";
	}
	
}
