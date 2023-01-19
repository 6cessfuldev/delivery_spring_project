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
	
	private static final long serialVersionUID = 1L;
	
	private String user_id;
	private String user_email;
	private String user_pw;
	private String user_name;
	private String user_phone;
	private String user_birth;
	private String user_naver_id;
	private String user_register_date;
	private String user_modify_date;
	private String user_Role;
	private String provider;    // oauth2를 이용할 경우 어떤 플랫폼을 이용하는지
    private String providerId;  // oauth2를 이용할 경우 아이디값

}
