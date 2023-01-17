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
public class UserVO {                                                             
	
	private String user_id;
	private String user_email;
	private String user_pw;
	private String user_name;
	private String user_phone;
	private String user_birth;
	private String user_naver_id;
	private String user_register_date;
	private String user_modify_date;
	private int user_isAdmin;
	//private String user_order_code;

	//주문하면 order_code는 현재 날자 + 랜덤숫자,,?
	//order_code 만들어서 review_order_code랑,,같으면 작성 가능?
}
