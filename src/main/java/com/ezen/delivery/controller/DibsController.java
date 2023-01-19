package com.ezen.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dibs/*")
public class DibsController {

	@GetMapping("/myDibsList")
	public String myDibsListGET(Model model) {
		return "member/myDibsList";
	}
	
}
