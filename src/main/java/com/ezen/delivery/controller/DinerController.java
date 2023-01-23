package com.ezen.delivery.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.delivery.domain.DibsVO;
import com.ezen.delivery.domain.DinerDTO;
import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.FileVO;
import com.ezen.delivery.domain.FoodDTO;
import com.ezen.delivery.domain.PagingVO;
import com.ezen.delivery.repository.UserDAO;
import com.ezen.delivery.security.oauth2.PrincipalDetails;
import com.ezen.delivery.service.DibsService;
import com.ezen.delivery.service.DinerService;
import com.ezen.delivery.service.FoodService;
import com.ezen.delivery.service.OrderService;

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
	
	@Inject
	private OrderService osv;
	
	@Inject
	private DibsService disv;
	
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
		
		List<DinerDTO> list = dsv.getList(pvo);
		
		log.info(list.size()+"");		
		model.addAttribute("dList", list);
		model.addAttribute("pvo", pvo);
	
		
		return "/diner/list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam(name="diner_code" )int diner_code, @RequestParam(name="order_code", required=false) String order_code, Authentication authentication, Model model) {
		log.info(""+diner_code);
		DinerDTO ddto = dsv.getDiner(diner_code);
		
		DinerVO diner = ddto.getDvo();
		FileVO fivo = ddto.getFivo();
		log.info("diner_code : "+diner.getDiner_code());
		log.info("diner_name : "+diner.getDiner_name());
		log.info("diner_address : "+diner.getDiner_address());
		log.info("count:"+diner.getDiner_review_count());
		List<FoodDTO> foodList = fsv.getListByDinerCode(diner_code);
		log.info(foodList.toString());
		
		int isDibs = 0;
		if(authentication != null) {
			PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
			String user_id = principalDetails.getUsername();	
			log.info(user_id);
			DibsVO divo = new DibsVO();
			divo.setDiner_code(diner_code);
			divo.setUser_id(user_id);
			isDibs = disv.countDibs(divo);
		}
		
		model.addAttribute("fivo", fivo);
		model.addAttribute("foodList",foodList);
		model.addAttribute("diner",diner);
		model.addAttribute("isDibs", isDibs);
		model.addAttribute("order_code", order_code);
		
		return "/diner/detail";
	}
	
	@GetMapping(value="/moreList/{listCnt}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<DinerDTO>> moreList(@PathVariable("listCnt") int listCnt, HttpSession session){
		
		PagingVO pvo = (PagingVO)session.getAttribute("pvo");
		if(pvo!=null) {
			pvo.setPageNum(listCnt);
		}
		List<DinerDTO> list = dsv.getList(pvo);
		log.info(list.size()+"");
		return new ResponseEntity<List<DinerDTO>>(list, HttpStatus.OK);

	}
	
	@GetMapping("/review")
	public String reviewPage(long order_code, RedirectAttributes rttr) {
		
		int diner_code = osv.getDinerCode(order_code); 
		return "redirect:/diner/detail?diner_code="+diner_code+"&order_code="+order_code;
	}

}
	
	
	