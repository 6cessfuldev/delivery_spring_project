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
	private String user_reg_date;
	private String user_mod_date;

}
