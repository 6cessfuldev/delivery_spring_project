package com.ezen.delivery.service;

import javax.servlet.http.HttpSession;

import com.ezen.delivery.domain.BasketListDTO;
import com.ezen.delivery.domain.OrderInfoDTO;

public interface OrderService {

//	String getToken() throws IOException;

	long orderPriceCheck(BasketListDTO bldto);

	String order(BasketListDTO bldto, OrderInfoDTO oidto, HttpSession session);



}
