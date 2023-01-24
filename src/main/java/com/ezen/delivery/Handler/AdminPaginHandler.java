package com.ezen.delivery.Handler;

import com.ezen.delivery.domain.AdminPagingVO;

import lombok.Data;

@Data
public class AdminPaginHandler {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int totalCount; // 전체 게시글 수
	private AdminPagingVO apvo;
	
	public AdminPaginHandler(AdminPagingVO apvo, int totalCount) {
		this.apvo = apvo;
		this.totalCount = totalCount;
		this.endPage = // 1~10 까지는 10의 값이 되도록 설정 
				(int)(Math.ceil(apvo.getPageNo() / (apvo.getQty()*1.0))) * apvo.getQty();
		this.startPage = endPage - 9; // 1~10 => 1
		
		int realEndPage = (int)Math.ceil((totalCount*1.0) / apvo.getQty());
		
		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}

}
