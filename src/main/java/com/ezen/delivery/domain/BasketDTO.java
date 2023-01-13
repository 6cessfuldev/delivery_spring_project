package com.ezen.delivery.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasketDTO {
	
	private int basket_code;
	private String user_id;
	private int food_code;
	private int basket_order_count;
	
	private List<ChoiceVO> choiceList;
	
	private String food_name;
	private int food_price;

	private int totalPrice;
	
	public void initSaleTotal() {
		this.totalPrice = this.food_price*this.basket_order_count;
	}
}
