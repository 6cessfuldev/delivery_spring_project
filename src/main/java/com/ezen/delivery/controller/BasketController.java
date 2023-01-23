package com.ezen.delivery.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.delivery.domain.BasketDTO;
import com.ezen.delivery.security.oauth2.PrincipalDetails;
import com.ezen.delivery.service.BasketService;
import com.ezen.delivery.service.FoodService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/basket/*")
public class BasketController {

	@Inject
	private BasketService bsv;
	@Inject
	private FoodService fsv;
	
	//등록성공 : 1 / 이미 데이터 있음 : 2 / 장바구니에 다른 음식점 메뉴 있음 : 3 / 로그인 필요 4
	@PostMapping("/add") 
	@ResponseBody
	public String addBasketPOST(@RequestBody BasketDTO basket, Authentication authentication) {
		
		log.info(basket.toString());
		
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		if(principalDetails == null) {
			return "4";
		}else{
			String user_id = principalDetails.getUsername();
			int prev_diner_code = bsv.getDinerCode(user_id);
			int next_diner_code = fsv.getFood(basket.getFood_code()).getDiner_code();
			if(prev_diner_code != next_diner_code && prev_diner_code!= 0) {
				return "3";
			}
		}
		
		
		return bsv.addBasket(basket)+"";
	}
	
	@PostMapping("/list")
	@ResponseBody
	public List<BasketDTO> getListPOST(String user_id) {
		
		log.info(user_id);
		
		List<BasketDTO> bdtoList = bsv.getList(user_id);
		
		return bdtoList;
	}
	
	@DeleteMapping("/{basket_code}")
	@ResponseBody
	public String basketDELETE(@PathVariable("basket_code")int basket_code) {
		
		return bsv.removeByBasketCode(basket_code)+"";
		
	}
	
	@PutMapping("/amount")
	@ResponseBody
	public String basketCountPUT(@ModelAttribute("basket") BasketDTO basket ) {
		
		return bsv.modifyCount(basket)+"";
	}
	
	@GetMapping("/diner")
	public String basketDinerGET(Authentication authentication, Model model) {
		log.info("basket/diner");
		if(authentication == null) {
			return "/member/login";
		}
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		
		String user_id = principalDetails.getUsername();
		log.info(user_id);
		
		//장바구니 값이 없을 경우 0
		int diner_code = bsv.getDinerCode(user_id);
		if(diner_code==0) return "redirect:/";
		
		return  "redirect:/diner/detail?diner_code="+diner_code;
	}
}