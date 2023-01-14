package com.ezen.delivery.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.ezen.delivery.domain.BasketVO;
import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.service.BasketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/basket/*")
public class BasketController {

	@Inject
	private BasketService bsv;
	
	//등록성공 : 1 / 이미 데이터 있음 : 2 / 로그인 필요 : 3
	@PostMapping("/add") 
	@ResponseBody
	public String addBasketPOST(@RequestBody BasketDTO basket, HttpServletRequest request) {
		
		log.info(basket.toString());
		
		HttpSession session = request.getSession();
		UserVO uvo = (UserVO)session.getAttribute("user");
		if(uvo == null) {
			return "3";
		}
		
		return bsv.addBasket(basket)+"";
	}
	
	@GetMapping("/list/{user_id}")
	@ResponseBody
	public List<BasketDTO> getListGET(@PathVariable("user_id") String user_id) {
		
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
	public String basketDinerGET(HttpSession session, Model model) {
		
		UserVO user = (UserVO)session.getAttribute("user");
		if(user == null) {
			return "/member/login";
		}
		
		int diner_code = bsv.getDinerCode(user.getUser_id());
		
		
		return 
	}
}