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
public class ChoiceVO {
	
	private int choice_code;
	private int food_code;
	private String choice_content;
	private int choice_price;
	
}
