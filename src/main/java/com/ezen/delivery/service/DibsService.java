package com.ezen.delivery.service;

import java.util.List;

import com.ezen.delivery.domain.DibsVO;

public interface DibsService {


	int creatOrDelete(DibsVO dvo);

	int countDibs(DibsVO divo);

	List<DibsVO> dibsList(String user_id);

}
