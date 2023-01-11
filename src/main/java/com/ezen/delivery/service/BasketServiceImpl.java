package com.ezen.delivery.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.delivery.domain.BasketDTO;
import com.ezen.delivery.repository.BasketDAO;

@Service
public class BasketServiceImpl implements BasketService {

	@Inject
	private BasketDAO bdao;
	
	@Override
	public int addBasket(BasketDTO basket) {
		
		BasketDTO checkBasket = bdao.checkBasket(basket);
		
		if(checkBasket != null) {
			return 2;
		}
		
		try {
			return bdao.insert(basket);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
