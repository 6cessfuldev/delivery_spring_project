package com.ezen.delivery.domain;

import org.apache.poi.hpsf.Decimal;

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
public class PagingVO {

	private String jibunAddr;
	private String lng;
	private String lat;
	private int category;
	private int pageNum;
	
	public int getStartNum() {
		if(pageNum==0)return 0;
		return 20+(pageNum-1)*10;
	}
	
}

// 0 1 2 3 4 5
//20 30 40 50
