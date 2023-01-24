package com.ezen.delivery.domain;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class OrderHistoryDTO {
	
	private long order_code;
	private String diner_name;
	private String choice_contents;
	private String food_name;
	private int total_price;
	private int order_count;
	private String user_email;
	private FileVO fivo;

}
