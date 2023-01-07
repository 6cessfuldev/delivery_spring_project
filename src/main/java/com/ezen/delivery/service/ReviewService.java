package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewVO;

public interface ReviewService {

	//리뷰등록(post)
//	int register(ReviewVO rvo);
	//리뷰등록(get)
	List<ReviewVO> getList(int review_diner_code);
	
	int insert(ReviewDTO ridto);

}
