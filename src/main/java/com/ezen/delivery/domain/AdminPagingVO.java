package com.ezen.delivery.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminPagingVO {
	
	private int pageNo;
	private int qty;
	
	private String type;
	private String keyword;
	
	public AdminPagingVO() {
		this(1,10);
	}
	
	public AdminPagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	public int getPageStart() { // 시작(start) 번호 구하는 메서드
		return (this.pageNo - 1) * qty; // DB에서 값을 limit 첫 시작이 0번지
	}

	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}

}
