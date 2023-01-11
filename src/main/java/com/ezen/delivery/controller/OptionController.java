package com.ezen.delivery.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.delivery.domain.OptionDTO;
import com.ezen.delivery.service.OptionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/option/*")
public class OptionController {

//	@Inject
//	private OptionService opsv;
	
//	@GetMapping(value="/{food_code}", produces= {MediaType.APPLICATION_JSON_VALUE})
//	public ResponseEntity<List<OptionDTO>> getOption(@PathVariable("food_code")int food_code){
//		
//		
//	}
	
}
