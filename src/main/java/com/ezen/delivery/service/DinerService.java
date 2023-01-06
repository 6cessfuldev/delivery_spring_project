package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.PagingVO;
import com.ezen.delivery.domain.DinerVO;

public interface DinerService {

	List<DinerVO> getList();

	int reviewFile(ReviewDTO rdto);

	int register(DinerVO dvo);

	List<DinerVO> getList(PagingVO pvo);






}
