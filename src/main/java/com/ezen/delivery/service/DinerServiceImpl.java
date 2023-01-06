package com.ezen.delivery.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.PagingVO;
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
		return ddao.selectListFirst();
	}

	@Override
	public int reviewFile(ReviewDTO rdto) {
		
		return 0;
	}

	@Override
	public int register(DinerVO dvo) {
		
		return ddao.insert(dvo);
	}

	@Override
	public List<DinerVO> getList(PagingVO pvo) {
		
		return ddao.selectList(pvo);
	}


}
