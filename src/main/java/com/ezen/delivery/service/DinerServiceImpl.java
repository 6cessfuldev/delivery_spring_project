package com.ezen.delivery.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.Handler.FileHandler;
import com.ezen.delivery.domain.AdminPagingVO;
import com.ezen.delivery.domain.DinerDTO;
import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.FileVO;
import com.ezen.delivery.domain.FoodDTO;
import com.ezen.delivery.domain.PagingVO;
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
	
	@Inject
	FileDAO fidao;
	
	@Inject
	FoodService fsv;
	
	@Inject
	FileHandler fhd;

	@Override
	public List<DinerVO> getList() {
		
		return ddao.selectListAll();
	}
	
	@Override
	public List<DinerVO> getListFirst() {
		return ddao.selectListFirst();
	}

	@Override
	public int register(DinerDTO ddto) {
		
		int isOk = 1;
		
		if(ddto.getFivo() != null) {
			isOk *= fidao.insert(ddto.getFivo());
			ddto.getDvo().setFile_code(ddto.getFivo().getFile_code());
		}
		
		isOk *= ddao.insert(ddto.getDvo()); 
		
		return isOk;
	}

	@Override
	public List<DinerDTO> getList(PagingVO pvo) {
		
		List<DinerDTO> ddtoList = new ArrayList<DinerDTO>();
		List<DinerVO> dvoList = null;
		
		if(pvo.getCategory().equals("all")) {
			switch (pvo.getOrder()) {
			case 1:
				dvoList = ddao.selectListByDistance(pvo);
				break;
			case 2:
				dvoList = ddao.selectListByReview(pvo);
				break;
			default :
				dvoList = ddao.selectListByScore(pvo);
				break;
			}
		}else {
			switch (pvo.getOrder()) {
			case 1:
				dvoList = ddao.selectCathListByDistance(pvo);
				break;
			case 2:
				dvoList = ddao.selectCathListByReview(pvo);
				break;
			default :
				dvoList = ddao.selectCathListByScore(pvo);
				break;
			}
		}
		
		for (DinerVO dvo : dvoList) {
			
			FileVO fvo = fidao.selectByFileCode(dvo.getFile_code());
			
			int diner_code = dvo.getDiner_code();
			double avg = rdao.selectAvgStar(diner_code);
			int count = rdao.reviewCount(diner_code);
			int commentCount = rdao.commentCount(diner_code);
			dvo.setDiner_boss_comment_count(commentCount);
			dvo.setDiner_review_count(count);
			dvo.setDiner_score_avg(avg);
			
			ddtoList.add(new DinerDTO(dvo,fvo));
		}
		
		return ddtoList;
	}

	public DinerDTO getDiner(int diner_code) {
		
		DinerVO dvo = ddao.selectDiner(diner_code);
		double avg = rdao.selectAvgStar(diner_code);
		log.info(avg+"");
		dvo.setDiner_score_avg(avg);
		int count = rdao.reviewCount(diner_code);
		dvo.setDiner_review_count(count);
		int commentCount = rdao.commentCount(diner_code);
		dvo.setDiner_boss_comment_count(commentCount);
		FileVO fivo = fidao.selectByFileCode(dvo.getFile_code());
		
		return new DinerDTO(dvo, fivo);
	}

	@Override
	public int update(DinerDTO ddto) {
		
		int isUp = 1;
		
		if(ddto.getFivo() != null) {
			if(ddto.getFivo().getFile_code() !=0) {
				isUp *= fidao.update(ddto.getFivo());				
			}else {
				isUp *= fidao.insert(ddto.getFivo());
				ddto.getDvo().setFile_code(ddto.getFivo().getFile_code());
			}
		}
		
		isUp *= ddao.update(ddto.getDvo());
		
		return isUp;
		
	}

	@Override
	public int remove(int diner_code) {
		
		int isDel = 1;
		isDel *= fidao.delete(ddao.selectDiner(diner_code).getFile_code());
		isDel *= ddao.delete(diner_code);
		
		List<FoodDTO> fList = fsv.getListByDinerCode(diner_code);
		
		for (FoodDTO foodDTO : fList) {
			isDel *= fhd.deleteFile(foodDTO.getFilevo());
			isDel *= fsv.remove(foodDTO.getFoodvo().getFood_code());
		}
		
		return isDel;
	}


	@Override
	public int totalCount() {
		return ddao.selectTotalCount();
	}

	@Override
	public List<DinerVO> getListwithAdminPaging(AdminPagingVO pgvo) {
		return ddao.selectWithAdminPaging(pgvo);
	}

}
