package com.ezen.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailDTO {
	
	private long order_code;
	private String food_info_JSON;

}
