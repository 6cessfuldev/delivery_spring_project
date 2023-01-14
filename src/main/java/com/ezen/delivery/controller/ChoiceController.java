package com.ezen.delivery.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.delivery.domain.ChoiceVO;
import com.ezen.delivery.domain.FoodDTO;
import com.ezen.delivery.service.ChoiceService;
import com.ezen.delivery.service.FoodService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/choice/*")
public class ChoiceController {

	@Inject
	private FoodService fsv;

	@Inject
	private ChoiceService csv;
	
	@ResponseBody
	@GetMapping(value="/list/{food_code}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public FoodDTO getChoiceList(@PathVariable("food_code") int food_code){
		log.info(""+food_code);
		List<ChoiceVO> choiceList = csv.getList(food_code);
		FoodDTO fdto = fsv.getDetail(food_code);
		fdto.setCList(choiceList);
		
		log.info(choiceList.toString());
		return fdto;
	}
	
}
