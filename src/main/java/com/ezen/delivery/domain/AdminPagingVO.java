package com.ezen.delivery.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminPagingVO {
	
	private int pageNo;
	private int qty;
	
	public AdminPagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	
	public AdminPagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	public int getPageStart() {
		return (this.pageNo-1)*this.qty;
	}
	
}
