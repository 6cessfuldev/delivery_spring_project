package com.ezen.delivery.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.PagingVO;
import com.ezen.delivery.repository.DinerDAO;
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
		
		return ddao.selectListAll();
	}
	
	@Override
	public List<DinerVO> getListFirst() {
		return ddao.selectListFirst();
	}

	@Override
	public int register(DinerVO dvo) {
		
		return ddao.insert(dvo);
	}

	@Override
	public List<DinerVO> getList(PagingVO pvo) {
		
		if(pvo.getCategory().equals("all")) {
			return ddao.selectList(pvo);
		}
		
		return ddao.selectListbyCate(pvo);
	}

	public DinerVO getDiner(int diner_code) {
		return ddao.selectDiner(diner_code);
	}

	@Override
	public int update(DinerVO dvo) {
		return ddao.update(dvo);
		
	}

	@Override
	public int remove(int diner_code) {
		
		return ddao.delete(diner_code);
	}



}
