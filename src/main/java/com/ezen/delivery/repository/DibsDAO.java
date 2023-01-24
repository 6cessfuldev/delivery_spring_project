package com.ezen.delivery.repository;

import java.util.List;

import com.ezen.delivery.domain.DibsVO;

public interface DibsDAO {

	int update(DibsVO dvo);

	int selectDibs(DibsVO dvo);

	int delete(DibsVO dvo);

	int insert(DibsVO dvo);

	int selectCountDibs(DibsVO divo);

	List<DibsVO> selectDibsList(String user_id);

}
