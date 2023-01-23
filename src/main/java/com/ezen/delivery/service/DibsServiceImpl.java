package com.ezen.delivery.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.DibsVO;
import com.ezen.delivery.repository.DibsDAO;

@Service
public class DibsServiceImpl implements DibsService {
	
	@Inject
	private DibsDAO didao;

	@Override
	public int creatOrDelete(DibsVO dvo) {
		
		int isExist = didao.selectDibs(dvo);
		
		//추가든지 삭제든지 여튼 작업 완료하면 1을 주기
		//이미 있으면 삭제, 없으면 추가
		int isUp = 0;
		if(isExist != 0) {
			isUp = didao.delete(dvo);
		}else {
			isUp = didao.insert(dvo);
		}
		return isUp ;
	}

}
