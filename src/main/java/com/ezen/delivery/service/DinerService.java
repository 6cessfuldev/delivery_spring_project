package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewVO;
import com.ezen.delivery.domain.PagingVO;
import com.ezen.delivery.domain.DinerDTO;
import com.ezen.delivery.domain.DinerVO;

public interface DinerService {

	List<DinerVO> getListFirst();

	DinerDTO getDiner(int diner_code);

	int register(DinerDTO ddto);

	List<DinerDTO> getList(PagingVO pvo);

	List<DinerVO> getList();

	int update(DinerDTO ddto);

	int remove(int diner_code);









}
