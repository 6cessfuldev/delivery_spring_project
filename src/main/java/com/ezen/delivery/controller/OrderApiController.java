package com.ezen.delivery.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.delivery.domain.BasketListDTO;
import com.ezen.delivery.domain.OrderInfoDTO;
import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.service.BasketService;
import com.ezen.delivery.service.OrderService;
import com.ezen.delivery.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrderApiController {
	
	@Inject
	private OrderService osv;
	
	@Inject
	private BasketService bsv;
	
	@Inject
	private PaymentService psv;
	
	
	@PostMapping("/order/payment/complete")
	public ResponseEntity<String> payment(HttpSession session, OrderInfoDTO oidto) throws IOException {
	    
		UserVO uvo = (UserVO)session.getAttribute("user");
		String user_id = uvo.getUser_id();
	    BasketListDTO bldto = bsv.getBasketListDTO(user_id);
	    int totalPrice = oidto.getAmount();
	    
	    long orderPriceCheck = osv.orderPriceCheck(bldto);
	    
	    System.out.println("계산금액 = " + totalPrice + " 실제 계산해야할 금액 = " + orderPriceCheck );
	    
	    if(orderPriceCheck == totalPrice) {
	        osv.order(bldto, oidto, session);
	        session.removeAttribute("BasketList");
	    }
	 
	    return ResponseEntity.ok().body("주문금액 일치");
	}
	

	@PostMapping("/order/payment/complete")
	public ResponseEntity<String> paymentComplete(OrderInfoDTO oidto, HttpSession session) throws IOException {
		log.info(oidto.toString());
		
		String token = psv.getToken();
		
//		UserVO user = (UserVO)session.getAttribute("user");
		
		
		 System.out.println("토큰 : " + token);
		    // 결제 완료된 금액
		    int amount = psv.paymentInfo(oidto.getIMP_UID(), token);
		    
//		    try {
//		        // 주문 시 사용한 포인트
//		        int usedPoint = oidto.getUsedPoint();
//		        
//		        if (user != null) {
//		            int point = user.getPoint();
//		            
//		            // 사용된 포인트가 유저의 포인트보다 많을 때
//		            if (point < usedPoint) {
//		            	psv.payMentCancel(token, oidto.getIMP_UID(), amount, "유저 포인트 오류");
//		                return new ResponseEntity<String>("유저 포인트 오류", HttpStatus.BAD_REQUEST);
//		            }
//		 
//		        } else {
//		            // 로그인 하지않았는데 포인트사용 되었을 때
//		            if (usedPoint != 0) {
//		            	psv.payMentCancel(token, oidto.getIMP_UID(), amount, "비회원 포인트사용 오류");
//		                return new ResponseEntity<String>("비회원 포인트 사용 오류", HttpStatus.BAD_REQUEST);
//		            }
//		        }
		        
		        BasketListDTO basketList = (BasketListDTO) session.getAttribute("basketList");
		        // 실제 계산 금액 가져오기
		        long orderPriceCheck = osv.orderPriceCheck(basketList);
		        
		        // 계산 된 금액과 실제 금액이 다를 때
		        if (orderPriceCheck != amount) {
		        	psv.payMentCancel(token, oidto.getIMP_UID(), amount, "결제 금액 오류");
		            return new ResponseEntity<String>("결제 금액 오류, 결제 취소", HttpStatus.BAD_REQUEST);
		        }
		        
//		        osv.order(basketList, oidto, user, session);
		        osv.order(basketList, oidto, session);
		        session.removeAttribute("basketList");
		        
		        return new ResponseEntity<>("주문이 완료되었습니다", HttpStatus.OK);
		        
//		    } catch (Exception e) {
//		    	psv.payMentCancel(token, oidto.getIMP_UID(), amount, "결제 에러");
//		        return new ResponseEntity<String>("결제 에러", HttpStatus.BAD_REQUEST);
//		    }
	}

}
