package com.ezen.delivery.domain;

import java.util.List;

import lombok.Data;

@Data
public class LoginDTO {
	
	private List<LoginVO> lList;
	private UserVO uvo;

}
