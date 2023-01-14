package com.ezen.delivery.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {
	
	private FoodVO foodvo;
	private FileVO filevo;
	private List<ChoiceVO> cList;
}
