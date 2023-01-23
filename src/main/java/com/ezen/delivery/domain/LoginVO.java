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
public class LoginVO {

	private int log_code;
	private String user_id;
	private String log_date;
	private String log_out_date;
	
}
