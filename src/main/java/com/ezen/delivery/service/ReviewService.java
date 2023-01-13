package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewImgVO;

public interface ReviewService {

	List<ReviewDTO> getList(int review_diner_code);
	
	int insert(ReviewDTO ridto);

//	int remove(int review_code);
//
//	ReviewImgVO selectFile(String review_img_uuid);
//
//	int deleteFile(String review_img_uuid);



}