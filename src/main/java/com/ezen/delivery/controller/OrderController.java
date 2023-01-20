package com.ezen.delivery.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.delivery.domain.BasketDTO;
import com.ezen.delivery.domain.OrderFoodDTO;
import com.ezen.delivery.domain.PayVO;
import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.security.oauth2.PrincipalDetails;
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
	
	@GetMapping("/page")
	public String orderPageGet(Authentication authentication, Model model) {
		
		System.out.println("orderrrr");
		
		System.out.println(authentication);
		
		if(authentication.getPrincipal() == null) {
			return "/member/login";
		}
		
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		
		String user_id = principalDetails.getUsername();
		
		List<BasketDTO> bList = bsv.getList(user_id);
		UserVO uvo = usv.getUserByID(user_id);
		
		int orderTotalPrice = 0;
		String orderName = "";
		
		orderName = "(먹어요)";
		
		for(BasketDTO bdto : bList) {
			orderName+=bdto.getFood_name();
			orderName+="/";
			orderTotalPrice += bdto.getTotal_price() * bdto.getBasket_order_count();
		}
		log.info(orderName);
		
		model.addAttribute("user", uvo);
		model.addAttribute("basketList", bList);
		model.addAttribute("order_name", orderName);
		
		model.addAttribute("orderTotalPrice", orderTotalPrice);
		
		return "/member/order";
	}

	@PostMapping("/")
	public String orderPagePOST(@ModelAttribute(value="OrderFoodDTO") OrderFoodDTO ofdto, String user_id, Model model) {
		
		log.info(">>> orderFoodDTO : " + ofdto.toString());
		log.info("user_id : " + user_id);
		return "/member/order";
	}

	@ResponseBody
	@PostMapping("/payment/complete")
	public String paymentComplete(PayVO pvo) throws IOException {
		log.info(pvo.toString());
		
		String token = osv.getToken();
		
		return "1";
	}

	@GetMapping("/myOrderList")
	public String orderListPageGET(HttpSession session, Model model) {
		UserVO user = (UserVO)session.getAttribute("user");
		
		//주문 내역 가져오기
		
		return "/member/myOrderList";
	}

	
}
