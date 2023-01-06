package com.ezen.delivery.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String insert(DinerVO dvo, @RequestParam List<String> category) {
		
		String diner_category = "";
		
		for (String string : category) {
			diner_category+=string;
		}
		dvo.setDiner_category(diner_category);
		
		dsv.register(dvo);
		log.info(dvo.toString());
		
		return "redirect:/admin/register";
	}
	
}
