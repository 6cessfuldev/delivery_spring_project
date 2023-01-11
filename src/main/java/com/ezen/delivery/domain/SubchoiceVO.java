package com.ezen.delivery.domain;

import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SubchoiceVO {
	
	private int subchoice_code;
	private int choice_code;
	private int subchoice_price;
	private String subchoice_content;

}
