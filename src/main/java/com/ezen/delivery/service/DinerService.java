package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.DestVO;
import com.ezen.delivery.domain.DinerVO;

public interface DinerService {

	List<DinerVO> getList();

	int reviewFile(ReviewDTO rdto);

	List<DinerVO> getFirstListByCategory(DestVO dsvo, int category);

	int register(DinerVO dvo);






}
