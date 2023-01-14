package com.ezen.delivery.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class OrderFoodDTO {

	// 뷰로부터 전달받을 값
	private int food_code;
	private int order_food_count;
	
	private List<ChoiceVO> choiceList;
	
	// DB로부터 꺼내올 값
	private String food_name;
	private int food_price;

	// 만들어 낼 값
	private int total_price;

	public void totalPrice(){
		this.total_price = this.order_food_count * this.food_price;
	}
		
}
