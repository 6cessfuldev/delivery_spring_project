package com.ezen.delivery.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.delivery.domain.DestVO;
import com.ezen.delivery.domain.DinerVO;

public interface DinerDAO {

	List<DinerVO> selectDinerList();

	List<DinerVO> selectTenDinerByCategory(DestVO dsvo, @Param("category") int category);

	int insert(DinerVO dvo);

}
