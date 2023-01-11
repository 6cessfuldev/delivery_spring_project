package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.ReviewDTO;

public interface ReviewService {

	//리뷰등록(post)
//	int register(ReviewVO rvo);
	//리뷰등록(get)
	List<ReviewDTO> getList(int review_diner_code);
	
	int insert(ReviewDTO ridto);



}

