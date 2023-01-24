package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewImgVO;
import com.ezen.delivery.domain.ReviewVO;


public interface ReviewService {

	List<ReviewDTO> getList(int diner_code);
	
	int insert(ReviewDTO ridto, int diner_code);

	int remove(int review_code);

	ReviewImgVO selectFile(int review_code);

	int deleteFile(int review_code);

	int bossComment(ReviewVO rvo);

	




}

