package com.ezen.delivery.domain;

import lombok.Data;

@Data
public class OrderInfoDTO {
	
	private long order_code;
	private String order_pg;
	private String order_pay_method;
	private String order_name;
	private int order_amount;
	private String order_buyer_email;
	private String order_buyer_name;
	private String order_buyer_tel;
	private String order_buyer_addr;
	private String order_buyer_postcode;
	private String order_m_redirect_url;
	private boolean order_niceMobileV2;
	private String order_IMP_UID;
	private int diner_code;
	
}
