package com.ezen.delivery.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.delivery.domain.BasketListDTO;
import com.ezen.delivery.domain.OrderInfoDTO;
import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.security.oauth2.PrincipalDetails;
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

	@PostMapping("/order/payment-cash")
	public ResponseEntity<String> paymentCash(Authentication authentication, OrderInfoDTO oidto) throws IOException {

		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		String user_id = principalDetails.getUsername();
		
		BasketListDTO bldto = bsv.getBasketListDTO(user_id);
		int totalPrice = oidto.getOrder_amount();

		long orderPriceCheck = osv.orderPriceCheck(bldto);

		System.out.println("계산금액 = " + totalPrice + " 실제 계산해야할 금액 = " + orderPriceCheck);

		if (orderPriceCheck == totalPrice) {
			osv.order(bldto, oidto);
		}

		return ResponseEntity.ok().body("주문금액 일치");
	}

	@PostMapping(value = "/order/payment/complete")
	public ResponseEntity<String> paymentComplete(Authentication authentication, OrderInfoDTO oidto) throws IOException {

		log.info(oidto.toString());

		String token = psv.getToken();

		System.out.println("토큰 : " + token);
		
		// 결제 완료된 금액
		int amount = psv.paymentInfo(oidto.getOrder_IMP_UID(), token);

		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		String user_id = principalDetails.getUsername();
		BasketListDTO bldto = bsv.getBasketListDTO(user_id);
		
		// 실제 계산 금액 가져오기
		long orderPriceCheck = osv.orderPriceCheck(bldto);

		// 계산 된 금액과 실제 금액이 다를 때
		if (orderPriceCheck != amount) {
			log.info("not equal");
			psv.payMentCancel(token, oidto.getOrder_IMP_UID(), amount, "결제 금액 오류");
			
			return ResponseEntity.badRequest().body("0");
		} 
			
		osv.order(bldto, oidto);
		log.info("complete");
		return ResponseEntity.ok().body("1");
			
		
	}

}
