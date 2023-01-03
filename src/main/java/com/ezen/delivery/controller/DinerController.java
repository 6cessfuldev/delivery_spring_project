package com.ezen.delivery.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	

	
	@GetMapping("/list")
	public String list(Model model) {
		List<DinerVO> list = dsv.getList();
		model.addAttribute("list",list);
		return "/diner/list";
	}
	
	
}
