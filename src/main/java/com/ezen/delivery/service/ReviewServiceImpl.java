package com.ezen.delivery.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewImgVO;
import com.ezen.delivery.domain.ReviewVO;
import com.ezen.delivery.repository.ReviewDAO;
import com.ezen.delivery.repository.ReviewImgDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

	@Inject
	ReviewDAO rdao;
	@Inject
	ReviewImgDAO ridao;

	//리뷰등록(get)
	@Override
	public List<ReviewVO> getList(int review_diner_code) {
		
		return rdao.selectReview(review_diner_code);
	}
	@Override
	public int insert(ReviewDTO ridto) {
		int isOk = rdao.insertReview(ridto.getRvo());
		for(ReviewImgVO rivo : ridto.getFList()) {			
			isOk *= ridao.insert(rivo);
		}
		return 0;
		
	}


}
