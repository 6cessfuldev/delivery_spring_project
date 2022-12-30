package com.ezen.delivery.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.repository.DinerDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DinerServiceImpl implements DinerService {

	@Inject
	private DinerDAO ddao;

	@Override
	public int insert(DinerVO dvo) {
		
		return ddao.insert(dvo);
	}
	
}
