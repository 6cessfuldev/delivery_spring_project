package com.ezen.delivery.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.delivery.domain.ChoiceDTO;
import com.ezen.delivery.service.ChoiceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/choice/*")
public class ChoiceController {

	@Inject
	private ChoiceService csv;
	
	@GetMapping(value="/{food_code}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ChoiceDTO>> getChoice(@PathVariable("food_code")int food_code){
		List<ChoiceDTO> list = csv.getList(food_code);
		return  new ResponseEntity<List<ChoiceDTO>>(list, HttpStatus.OK);
				
	}
	
}
