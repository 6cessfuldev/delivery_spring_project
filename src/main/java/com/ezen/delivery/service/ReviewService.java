package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.ReviewDTO;


public interface ReviewService {

	List<ReviewDTO> getList(int review_diner_code);
	
	int insert(ReviewDTO ridto);

	
	//사장님댓글
	String bossComment(int review_diner_code, String review_boss_comment);


//	int remove(int review_code);
//
//	ReviewImgVO selectFile(String review_img_uuid);
//
//	int deleteFile(String review_img_uuid);



}

