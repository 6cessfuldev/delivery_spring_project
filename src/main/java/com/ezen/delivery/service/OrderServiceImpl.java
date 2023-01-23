package com.ezen.delivery.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.BasketDTO;
import com.ezen.delivery.domain.BasketListDTO;
import com.ezen.delivery.domain.OrderDetailDTO;
import com.ezen.delivery.domain.OrderInfoDTO;
import com.ezen.delivery.repository.BasketDAO;
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
	
	@Inject
	private BasketDAO bdao;

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
	public String order(BasketListDTO bldto, OrderInfoDTO oidto) {
		
		Gson gson = new Gson();
		
		System.out.println("info = " + oidto);
		
		int total = bldto.getTotalPrice();
		
		oidto.setDiner_code(bldto.getDiner_code());
		oidto.setOrder_amount(total);

		
		List<BasketDTO> basketList = bldto.getBasketList();
		
		OrderDetailDTO[] oddto = new OrderDetailDTO[basketList.size()];
		
		
		for(int i=0;i<oddto.length;i++) {
			String basketJSON = gson.toJson(basketList.get(i));
			oddto[i] = new OrderDetailDTO(oidto.getOrder_code(), basketJSON);
		}
		
		odao.order(oidto);
		
		log.info(oddto.toString());
		odao.orderDetail(oddto);
		
		for (BasketDTO bdto : basketList) {
			bdao.delete(bdto.getBasket_code());
		}
		
		return null;
	}

	@Override
	public List<OrderInfoDTO> orderInfoDTOList(String user_email) {
		return odao.orderInfoDTOList(user_email);
	}

	@Override
	public List<OrderDetailDTO> orderDetailDTOList(long order_code) {
		return odao.orderDetailDTOList(order_code);
	}

	@Override
	public String getDinerName(long order_code) {
		return odao.getDinerName(order_code);
	}

	@Override
	public int getDinerCode(long order_code) {
		return odao.selectDinerCode(order_code);
	}

	
	

}
