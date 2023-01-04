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
@RequestMapping("/diner/*")
public class DinerController {

	@Inject
	private DinerService dsv;
	
	@GetMapping("/register")
	public void register() {
		
		log.info("diner/register");
		
		DinerVO dvo = new DinerVO();
		dvo.setName("피자에땅");
		
		int isOk = dsv.insert(dvo);
		log.info(isOk+"");
	}
	
}
