package com.ezen.delivery.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.BasketDTO;
import com.ezen.delivery.domain.BasketListDTO;
import com.ezen.delivery.domain.OrderDetailDTO;
import com.ezen.delivery.domain.OrderInfoDTO;
import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.repository.OrderDAO;
import com.google.gson.Gson;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@PropertySource("classpath:application.yml")
public class OrderServiceImpl implements OrderService {
	
	@Inject
	private OrderDAO odao;

	@Value("${import.imp_key}")
	private String imp_key;
	
	@Value("${import.imp_secret}")
	private String imp_secret;
	
	@Data
	private class Response{
		private PaymentInfo response;
	}
	
	@Data
	private class PaymentInfo{
		private int amount;
	}
	

	@Override
	public long orderPriceCheck(BasketListDTO bldto) {
		
		log.info("basketDetail = " + bldto);
		
		List<BasketDTO> basket = bldto.getBasketList();
		log.info(basket.toString());
		List<Integer> foodPriceList = odao.foodPriceList(basket);
		List<Integer> choicePriceList = odao.choicePriceList(basket);
		int deliveryFee = odao.getDeliveryFee(bldto.getDiner_code());
		
		log.info("foodPriceList = " + foodPriceList);
		log.info("choicePriceList = " + choicePriceList);
		
		int sum = 0;
		
		for(int i=0 ; i<basket.size() ; i++) {
			int foodPrice = foodPriceList.get(i);
			int amount = basket.get(i).getBasket_order_count();
			int choicePrice = choicePriceList.get(i);
			
			sum += (foodPrice + choicePrice) * amount;
		}
	
		return sum + deliveryFee;
	}

	@Override
	public String order(BasketListDTO bldto, OrderInfoDTO oidto, HttpSession session) {
		
		Gson gson = new Gson();
		
		System.out.println("info = " + oidto);
		
		int total = bldto.getTotalPrice();
		
		oidto.setDiner_code(bldto.getDiner_code());
		oidto.setAmount(total);

		
		UserVO user = (UserVO)session.getAttribute("user");
		String user_id = user.getUser_id();
		oidto.setUser_id(user_id);

		
		List<BasketDTO> basketList = bldto.getBasketList();
		
		OrderDetailDTO[] oddto = new OrderDetailDTO[basketList.size()];
		
		
		for(int i=0;i<oddto.length;i++) {
			String basketJSON = gson.toJson(basketList.get(i));
			oddto[i] = new OrderDetailDTO(oidto.getOrder_food_code(), basketJSON);
		}
		
		odao.order(oidto);
		
		
		Map<String, Object> orderDetailMap = new HashMap<>(); 
		orderDetailMap.put("user_id", user_id);
		orderDetailMap.put("detail", oddto); 
		odao.orderDetail(orderDetailMap);
		
		
		return null;
	}
	

}
