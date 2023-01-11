package com.ezen.delivery.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.repository.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Inject
	private OrderDAO odao;
	

}
