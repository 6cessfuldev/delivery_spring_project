package com.ezen.delivery.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasketDTO {
	
	// 뷰로부터 전달받을 값
	private int basket_code;
	private String user_id;
	private int food_code;
	private int basket_order_count;
	
	//뷰에서는 choice_code만 받고 DB에서는 전체 꺼내올 값
	private List<ChoiceVO> choiceList;
	
	// DB로부터 꺼내올 값
	private String food_name;
	private int food_price;

	private String basket_content="";
	private int total_price;
	
	public void initBasket_content() {
		this.basket_content += food_name+" : ";
		for (ChoiceVO cvo : choiceList) {
			this.basket_content += cvo.getChoice_content()+" ";
		}
	}
	
	public void initSalePerOne() {
		int optionTotal = 0;
		for (ChoiceVO choiceVO : choiceList) {
			optionTotal+=choiceVO.getChoice_price();
		}
		this.total_price = optionTotal+food_price;			
	}
}
