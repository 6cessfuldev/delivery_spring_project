package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.ReviewDTO;

public interface ReviewService {

	List<ReviewDTO> getList(int review_diner_code);
	
	int insert(ReviewDTO ridto);



}