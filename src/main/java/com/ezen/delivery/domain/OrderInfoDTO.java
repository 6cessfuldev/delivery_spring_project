package com.ezen.delivery.domain;

import lombok.Data;

@Data
public class OrderInfoDTO {
	
	private String pg;
	private String pay_method;
	private String merchant_uid;
	private String name;
	private int amount;
	private String buyer_email;
	private String buyer_name;
	private String buyer_tel;
	private String buyer_addr;
	private String buyer_postcode;
	private String m_redirect_url;
	private boolean niceMobileV2;
	private String IMP_UID;
	private long order_food_code;
	private int diner_code;
	private String user_id;
	
}
