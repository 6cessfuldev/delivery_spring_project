package com.ezen.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FoodVO {

	private int food_code;
	private String food_name;
	private int food_price;
	private String food_intro;
	private int food_file_code;
	private String food_reg_date;
	private String food_state;
	private int food_diner_code;
}
