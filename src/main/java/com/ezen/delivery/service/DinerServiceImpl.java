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
	ReviewImgVO rivo;
	@Inject
	ReviewVO rvo;
	@Inject
	ReviewImgDAO ridao;
	
	@Override
	public List<DinerVO> getList() {
		return ddao.selectDinerList();
	}

	@Override
	public int reviewFile(ReviewDTO rdto) {
		// TODO Auto-generated method stub
		return 0;
	}


//	@Override
//	public int reviewFile(ReviewDTO rdto) {
//		int isOk = ridao.insertFile(rdto.getFList());
//		//여러개를,,등록,,,,
//		if(isOk>0 && rdto.getFList().size()>0) {
//			//가장 큰 파일코드 가져오기!
//			int  rimgfc = 
//			for(ReviewVO rvo : rdto.getFList()){
//				rvo.setRFileCode(rFileCode);
//				log.info("insert Review : "+rvo.toString());
//				isOk += rdao.insertFile(rvo);
//			}
//			
//		}  
//		return 0;
//
//	}
}
