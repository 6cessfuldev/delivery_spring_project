package com.ezen.delivery.service;

import java.util.ArrayList;
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


	@Override
	public List<ReviewDTO> getList(int review_diner_code) {
		List<ReviewVO> list = rdao.selectReview(review_diner_code);
		
		List<ReviewDTO> rdtoList = new ArrayList<>(); 
		for(int i=0; i<list.size(); i++) {
			ReviewDTO rdto = new ReviewDTO();
			
			rdto.setRvo(list.get(i));
			
			int reviewCode = list.get(i).getReview_code();
			
			List<ReviewImgVO> flist = ridao.selectFlist(reviewCode);
			rdto.setFList(flist);
			
			rdtoList.add(rdto);
		}
		
		return rdtoList;
	}
	@Override
	public int insert(ReviewDTO ridto) {
		int isOk = rdao.insertReview(ridto.getRvo());
		for(ReviewImgVO rivo : ridto.getFList()) {			
			rivo.setReview_code(ridto.getRvo().getReview_code());
			isOk *= ridao.insert(rivo);
		}
		return 0;
		
	}




}
