package com.ezen.delivery.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.ReviewVO;
import com.ezen.delivery.repository.ReviewDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

	@Inject
	ReviewDAO rdao;
	//리뷰등록(post)
	@Override
	public int register(ReviewVO rvo) {
		
		return rdao.insertReview(rvo);
	}
	//리뷰등록(get)
	@Override
	public List<ReviewVO> getList(int review_diner_code) {
		
		return rdao.selectReview(review_diner_code);
	}

}
