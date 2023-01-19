package com.ezen.delivery.repository;

import java.util.List;
import java.util.Map;

import com.ezen.delivery.domain.BasketDTO;
import com.ezen.delivery.domain.OrderDetailDTO;
import com.ezen.delivery.domain.OrderInfoDTO;

public interface OrderDAO {

	List<Integer> foodPriceList(List<BasketDTO> basket);

	List<Integer> choicePriceList(List<BasketDTO> basket);

	int getDeliveryFee(int diner_code);

	void order(OrderInfoDTO oidto);

	void orderDetail(OrderDetailDTO[] oddto);

	

}
