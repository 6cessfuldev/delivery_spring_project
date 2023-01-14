package com.ezen.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class BasketVO {

	private int basket_code;
	private String user_id;
	private int food_code;
	private int basket_order_count;
	
}
