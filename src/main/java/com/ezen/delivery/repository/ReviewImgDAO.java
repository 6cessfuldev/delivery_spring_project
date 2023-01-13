package com.ezen.delivery.repository;

import java.util.List;

import com.ezen.delivery.domain.ReviewImgVO;

public interface ReviewImgDAO {

	int insert(ReviewImgVO rivo);
	////이미지파일(diner컨트롤러랑 연결)
//	int insertImg(ReviewImgVO rivo);

	List<ReviewImgVO> selectFlist(int reviewCode);

//	ReviewImgVO selectFile(String review_img_uuid);
//
//	int deleteFile(String review_img_uuid);

}