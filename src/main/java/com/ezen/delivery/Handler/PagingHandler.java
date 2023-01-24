package com.ezen.delivery.Handler;

import com.ezen.delivery.domain.AdminPagingVO;

import lombok.Data;

@Data
public class PagingHandler {

	private int startPage;
	private int endPage;
	private int totalCount;
	
	private boolean prev, next;
	
	private AdminPagingVO pgvo;
	
	public PagingHandler(AdminPagingVO pgvo, int totalCount) {
		
		this.pgvo=pgvo;
		this.totalCount=totalCount;
		int realEndPage = totalCount/pgvo.getQty()+1;
		
		this.endPage = (int)Math.ceil(pgvo.getPageNo()/(pgvo.getQty()*1.0))*pgvo.getQty();
		this.startPage = endPage-pgvo.getQty()+1;
		
		if(endPage>realEndPage) {
			endPage = realEndPage;
		}
		
		if(startPage>1) prev = true;
		if(endPage<realEndPage) {
			next = true;
		}
	}
	
}