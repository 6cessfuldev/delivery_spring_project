package com.ezen.delivery.repository;

import java.util.List;

import com.ezen.delivery.domain.ReviewImgVO;

public interface ReviewImgDAO {

	int insert(ReviewImgVO rivo);

	List<ReviewImgVO> selectFlist(int reviewCode);

	ReviewImgVO selectFile(int review_code);

	int deleteFile(int review_code);



}

