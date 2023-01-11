package com.ezen.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DinerVO {

	private int diner_code;
	private int diner_file_code;
	private String diner_name;
	private int diner_min_pay;
	private String diner_address;
	private String diner_notice;
	private String diner_open_time;
	private String diner_close_time;
	private String diner_method_pay;
	private String diner_business_name;
	private String diner_company_num;
	private String diner_category;
	private String diner_addr_lng;
	private String diner_addr_lat;
}
