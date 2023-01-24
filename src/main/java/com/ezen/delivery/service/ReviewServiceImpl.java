package com.ezen.delivery.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewImgVO;
import com.ezen.delivery.domain.ReviewVO;
import com.ezen.delivery.repository.DinerDAO;
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
	@Inject
	DinerDAO ddao;

	@Override
	public List<ReviewDTO> getList(int diner_code) {
		List<ReviewVO> list = rdao.selectReview(diner_code);
		
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
	public int insert(ReviewDTO ridto, int diner_code) {
		int isOk = rdao.insertReview(ridto.getRvo());
		if(ridto.getFList() != null) {
			for(ReviewImgVO rivo : ridto.getFList()) {			
				rivo.setReview_code(ridto.getRvo().getReview_code());
				isOk *= ridao.insert(rivo);
			}
		}
		rdao.updateCount(diner_code);

		double scoreAvg = rdao.selectAvgStar(diner_code);
		DinerVO dvo = new DinerVO();
		dvo.setDiner_code(diner_code);
		dvo.setDiner_score_avg(scoreAvg);
		isOk *= ddao.updateScore(dvo);
		

		return 0;
	}


	//삭제
	@Override
	public int remove(int review_code) {
		
		return rdao.delete(review_code);
	}
	
	@Override
	public ReviewImgVO selectFile(int review_code) {
		
		return ridao.selectFile(review_code);
	}
	
	@Override
	public int deleteFile(int review_code) {
		
		return ridao.deleteFile(review_code);
	}
	
	@Override
	public int bossComment(ReviewVO rvo) {
		int isUp = rdao.updateComment(rvo);
		return isUp;
	}

	
	
	
	


}
