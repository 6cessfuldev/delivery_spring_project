package com.ezen.delivery.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.delivery.domain.DinerDTO;
import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.FoodDTO;
import com.ezen.delivery.domain.PagingVO;
import com.ezen.delivery.repository.UserDAO;
import com.ezen.delivery.service.DinerService;
import com.ezen.delivery.service.FoodService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/diner/*")
public class DinerController {

	@Inject
	private DinerService dsv;
	@Inject
	private UserDAO userDao;
	@Inject
	private FoodService fsv;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<DinerVO> list = dsv.getListFirst();
		model.addAttribute("list",list);
		return "/diner/list";
	}
	
	@GetMapping("/search")
	public String search (PagingVO pvo, HttpSession session, Model model) {
		
		log.info("destination : " + pvo.getJibunAddr());
		
		//세션에 위치정보 등록/수정하기
		session.setAttribute("pvo", pvo);
	
		
		log.info("Session : "+session.getAttribute("pvo"));
		
		List<DinerVO> list = dsv.getList(pvo);
		
		log.info(list.size()+"");		
		
		model.addAttribute("dList", list);
		model.addAttribute("pvo", pvo);
		
		return "/diner/list";
	}
	
	@GetMapping("/detail")
	public void detail(@RequestParam(name="diner_code" )int diner_code, Model model) {
		log.info(""+diner_code);
		
		DinerDTO ddto = dsv.getDiner(diner_code);
		
		DinerVO diner = ddto.getDvo();
		log.info("diner_code : "+diner.getDiner_code());
		log.info("diner_name : "+diner.getDiner_name());
		log.info("diner_address : "+diner.getDiner_address());
		List<FoodDTO> foodList = fsv.getListByDinerCode(diner_code);
		model.addAttribute("foodList",foodList);
		model.addAttribute("diner",diner);
	}
	
	@GetMapping(value="/moreList/{listCnt}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<DinerVO>> moreList(@PathVariable("listCnt") int listCnt, HttpSession session){
		
		PagingVO pvo = (PagingVO)session.getAttribute("pvo");
		if(pvo!=null) {
			pvo.setPageNum(listCnt);
		}
		List<DinerVO> list = dsv.getList(pvo);
		log.info(list.size()+"");
		return new ResponseEntity<List<DinerVO>>(list, HttpStatus.OK);

	}

}
	
	
	