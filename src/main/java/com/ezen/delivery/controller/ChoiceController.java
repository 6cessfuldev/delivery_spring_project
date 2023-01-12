package com.ezen.delivery.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.delivery.domain.ChoiceVO;
import com.ezen.delivery.service.ChoiceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/choice/*")
public class ChoiceController {


	@Inject
	private ChoiceService csv;

//	@GetMapping(value="/{food_code}", produces= {MediaType.APPLICATION_JSON_VALUE})
//	public ResponseEntity<List<ChoiceVO>> getChoice(@PathVariable("food_code")int food_code){
//		List<ChoiceVO> list = csv.getList(food_code);
//		return  new ResponseEntity<List<ChoiceVO>>(list, HttpStatus.OK);
//	}
	
	@ResponseBody
	@GetMapping(value="/list/{food_code}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public List<ChoiceVO> getChoiceList(@PathVariable("food_code") int food_code, Model model){
		List<ChoiceVO> choiceList = csv.getList(food_code);
		model.addAttribute("choiceList", choiceList);
		return choiceList;
	}
	
}
