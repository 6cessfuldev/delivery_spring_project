package com.ezen.delivery.repository;

import java.util.List;

import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewVO;

public interface ReviewDAO {


	//리뷰등록(get)
	List<ReviewVO> selectReview(int review_diner_code);
//dinerController//이미지파일(dinerService랑 연결)랑 연결
//	int insertReview(ReviewVO rvo);

	int insertReview(ReviewVO rvo);

	double selectAvgStar(int diner_code);


}