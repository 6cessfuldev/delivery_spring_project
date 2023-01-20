package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewImgVO;


public interface ReviewService {

	List<ReviewDTO> getList(int diner_code);
	
	int insert(ReviewDTO ridto, int diner_code);
	
	//사장님댓글
	//String bossComment(int diner_code, String review_boss_comment);

	int remove(int review_code);

	ReviewImgVO selectFile(int review_code);

	int deleteFile(int review_code);




}

