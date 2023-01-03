package com.ezen.delivery.repository;

import java.util.List;

import com.ezen.delivery.domain.DinerVO;

public interface DinerDAO {

	List<DinerVO> selectDinerList();

}
