package com.ezen.delivery.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.delivery.domain.BasketDTO;
import com.ezen.delivery.domain.OrderFoodDTO;
import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.service.BasketService;
import com.ezen.delivery.service.OrderService;
import com.ezen.delivery.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/order/*")
@Controller
public class OrderController {
	
	@Inject
	private OrderService osv;

	@Inject
	private BasketService bsv;
	
	@Inject
	private UserService usv;
	
	@GetMapping("/order/{user_id}")
	public String orderPageGet(@PathVariable("user_id") String user_id, Model model, HttpServletRequest req) {
		
		List<BasketDTO> bList = bsv.getList(user_id);
		UserVO uvo = usv.getUserByID(user_id);
		
		int orderTotalPrice = 0;
		
		for(BasketDTO bdto : bList) {
			orderTotalPrice += bdto.getTotal_price() * bdto.getBasket_order_count();
		}

		model.addAttribute("user", uvo);
		model.addAttribute("basketList", bList);
		model.addAttribute("orderTotalPrice", orderTotalPrice);
		
		return "/member/order";
	}
	
	@PostMapping("/")
	public String orderPagePOST(@ModelAttribute(value="OrderFoodDTO") OrderFoodDTO ofdto, String user_id, Model model) {
		
		log.info(">>> orderFoodDTO : " + ofdto.toString());
		log.info("user_id : " + user_id);
		return "/member/order";
	}

	@GetMapping("/myOrderList")
	public String orderListPageGET(HttpSession session, Model model) {
		UserVO user = (UserVO)session.getAttribute("user");
		
		//주문 내역 가져오기
		
		return "/member/myOrderList";
	}

}
