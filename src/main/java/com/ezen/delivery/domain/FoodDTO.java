package com.ezen.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {
	
	private FoodVO foodvo;
	private FileVO filevo;
	
}
