package com.ezen.delivery.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewImgVO;
import com.ezen.delivery.domain.ReviewVO;
import com.ezen.delivery.repository.DinerDAO;
import com.ezen.delivery.repository.FileDAO;
import com.ezen.delivery.repository.ReviewDAO;
import com.ezen.delivery.repository.ReviewImgDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DinerServiceImpl implements DinerService {

	@Inject
	DinerDAO ddao;
	@Inject
	ReviewImgDAO ridao;
	@Inject
	ReviewDAO rdao;
	
	@Override
	public List<DinerVO> getList() {
		return ddao.selectDinerList();
	}

	//이미지파일(diner컨트롤러랑 연결)
//	 @Override public int reviewFile(ReviewDTO rdto) { 
//	 int isOk = rdao.insertReview(rdto.getRvo()); 
//		 if(isOk > 0 && rdto.getFList().size() > 0){ 
//			 int rcode = rdto.getRvo().getReview_code(); 
//			 for(ReviewImgVO rivo : rdto.getFList()) { 
//				 rivo.setReview_code(rcode); 
//				 isOk *= ridao.insertImg(rivo);
//			 } 
//		 } return isOk;	 
//	 }
 

	@Override
	public DinerVO getDiner(int diner_code) {
		
		return ddao.selectDiner(diner_code);
	}


}
