package com.ezen.delivery.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.delivery.domain.BasketDTO;
import com.ezen.delivery.domain.PayVO;
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
	
	@ResponseBody
	@PostMapping("/payment/complete")
	public ResponseEntity<String> paymentComplete(PayVO pvo) throws IOException {
		log.info(pvo.toString());
		
		String token = osv.getToken();
		
		 System.out.println("토큰 : " + token);
		    // 결제 완료된 금액
		    int amount = paymentService.paymentInfo(orderInfo.getImpUid(), token);
		    
		    try {
		        // 주문 시 사용한 포인트
		        int usedPoint = orderInfo.getUsedPoint();
		        
		        if (user != null) {
		            int point = user.getPoint();
		            
		            // 사용된 포인트가 유저의 포인트보다 많을 때
		            if (point < usedPoint) {
		                paymentService.payMentCancle(token, orderInfo.getImpUid(), amount, "유저 포인트 오류");
		                return new ResponseEntity<String>("유저 포인트 오류", HttpStatus.BAD_REQUEST);
		            }
		 
		        } else {
		            // 로그인 하지않았는데 포인트사용 되었을 때
		            if (usedPoint != 0) {
		                paymentService.payMentCancle(token, orderInfo.getImpUid(), amount, "비회원 포인트사용 오류");
		                return new ResponseEntity<String>("비회원 포인트 사용 오류", HttpStatus.BAD_REQUEST);
		            }
		        }
		        
		        CartListDto cartList = (CartListDto) session.getAttribute("cartList");
		        // 실제 계산 금액 가져오기
		        long orderPriceCheck = orderService.orderPriceCheck(cartList)  - usedPoint;
		        
		        // 계산 된 금액과 실제 금액이 다를 때
		        if (orderPriceCheck != amount) {
		            paymentService.payMentCancle(token, orderInfo.getImpUid(), amount, "결제 금액 오류");
		            return new ResponseEntity<String>("결제 금액 오류, 결제 취소", HttpStatus.BAD_REQUEST);
		        }
		        
		        orderService.order(cartList, orderInfo, user, session);
		        session.removeAttribute("cartList");
		        
		        return new ResponseEntity<>("주문이 완료되었습니다", HttpStatus.OK);
		        
		    } catch (Exception e) {
		        paymentService.payMentCancle(token, orderInfo.getImpUid(), amount, "결제 에러");
		        return new ResponseEntity<String>("결제 에러", HttpStatus.BAD_REQUEST);
		    }
		
//		return "1";
	}
	
	
//	@PostMapping("/")
//	public String orderPagePOST(OrderFoodDTO ofdto, Model model) {
//		
//		log.info(">>> orders : " + ofdto.toString());
//		
//		return "/member/order";
//	}

}
