package com.ezen.delivery.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasketListDTO {
	
	private int diner_code;
	private String diner_name;
	int totalPrice;
	private int diner_delivery_fee;
	
	List<BasketDTO> basketList;
}