package com.ezen.delivery;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.delivery.service.BasketService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Inject
	private BasketService bsv;

	@RequestMapping(value = {"/index","/"}, method = RequestMethod.GET)
	public String index() {
		
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
	
	@RequestMapping(value = "/throw", method = RequestMethod.GET)
	public void testThrow() throws Exception {	
		
		throw new Exception("예외 테스트 입니다.");
		

	}
}
