package com.ezen.delivery.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ezen.delivery.domain.BasketListDTO;
import com.ezen.delivery.domain.OrderDetailDTO;
import com.ezen.delivery.domain.OrderInfoDTO;

public interface OrderService {

	long orderPriceCheck(BasketListDTO bldto);

	String order(BasketListDTO bldto, OrderInfoDTO oidto);

	List<OrderInfoDTO> orderInfoDTOList(String user_email);

	List<OrderDetailDTO> orderDetailDTOList(long order_code);

	String getDinerName(long order_code);

	int getDinerCode(long order_code);



}
