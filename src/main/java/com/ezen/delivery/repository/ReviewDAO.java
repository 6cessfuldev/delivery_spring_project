package com.ezen.delivery.repository;

import java.util.List;
import java.util.Map;

import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewVO;

public interface ReviewDAO {


	//리뷰등록(get)
	List<ReviewVO> selectReview(int review_diner_code);

	int insertReview(ReviewVO rvo);

	double selectAvgStar(int diner_code);

	int delete(int review_code);

	void bossComment(Map<String, Object> map);


}