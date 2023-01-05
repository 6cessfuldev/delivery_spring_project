package com.ezen.delivery.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.DestVO;
import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewImgVO;
import com.ezen.delivery.domain.ReviewVO;
import com.ezen.delivery.repository.DinerDAO;
import com.ezen.delivery.repository.ReviewImgDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DinerServiceImpl implements DinerService {

	@Inject
	DinerDAO ddao;

	@Inject
	ReviewImgDAO ridao;
	
	@Override
	public List<DinerVO> getList() {
		return ddao.selectDinerList();
	}

	@Override
	public int reviewFile(ReviewDTO rdto) {
		
		return 0;
	}

	@Override
	public List<DinerVO> getFirstListByCategory(DestVO dsvo, int category) {
		
		return ddao.selectTenDinerByCategory(dsvo, category);
	}

	@Override
	public int register(DinerVO dvo) {
		
		return ddao.insert(dvo);
	}


}
