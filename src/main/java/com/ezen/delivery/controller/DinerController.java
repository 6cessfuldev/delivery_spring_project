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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.delivery.Handler.FileHandler;
import com.ezen.delivery.domain.DestVO;
import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewImgVO;
import com.ezen.delivery.domain.ReviewVO;
import com.ezen.delivery.service.DinerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/diner/*")
public class DinerController {

	@Inject
	private DinerService dsv;
	@Inject
	private FileHandler fhd;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<DinerVO> list = dsv.getList();
		model.addAttribute("list",list);
		return "/diner/list";
	}
	
	@GetMapping("/search")
	public String search (DestVO dsvo, int category, HttpSession session, Model model) {
		
		log.info("category : "+category);
		log.info("destination : " + dsvo.getJibunAddr());
		
		//세션에 카테고리 등록/수정하기
		if(session.getAttribute("category") !=null) {
			int sessionCate = (int) session.getAttribute("category");
			if(category != sessionCate) {
				session.setAttribute("category", category);
				log.info("category change");
			}
		}else {
			session.setAttribute("category", category);
		}
		
		//세션에 위치정보 등록/수정하기
		if(session.getAttribute("addr") !=null) {
			DestVO sessionDvo = (DestVO)session.getAttribute("addr"); 
			if(!dsvo.getJibunAddr().equals(sessionDvo.getJibunAddr())) {
				session.setAttribute("addr", sessionDvo);
				log.info("addr change");
			}
		}else {
			session.setAttribute("addr", dsvo);
		}
		
		log.info("Session : "+session.getAttribute("category")+session.getAttribute("addr"));
		
		List<DinerVO> list = dsv.getFirstListByCategory(dsvo, category);
		
		log.info(list.size()+"");
		log.info(list.get(0).getDiner_name());
		
		
//		if(list ==null || list.size()==0) {
//			log.info("list is empty");
//		}else {			
//			log.info("list is not empty");
//		}
		
		model.addAttribute("dList", list);
		model.addAttribute("addr", dsvo);
		
		return "/diner/list";
	}
	
	@GetMapping("/detail")
	public void detail() {}


	@PostMapping("/detail")
	public String detailReview(ReviewVO rvo, RedirectAttributes rttr, @RequestParam(name="files", required = false )MultipartFile[] files) {
	List<ReviewImgVO> fList = null;
		if(files[0].getSize()>0) {
			fList = fhd.uploadFiles(files);
		}else {
			log.info("file null");
		}
		ReviewDTO ddto = new ReviewDTO(rvo, fList);
		int isOk = dsv.reviewFile(new ReviewDTO(rvo, fList));
		rttr.addAttribute("isOk", isOk>0 ? "1":"0");
		log.info("reviewFile register >> "+ (isOk>0 ? "OK":"FAIL"));
		return "/diner/detail";	
	}
	
	@GetMapping(value="/moreList", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<DinerVO>> moreList(){
		
		List<DinerVO> list = dsv.getList();
		log.info(list.size()+"");
		return new ResponseEntity<List<DinerVO>>(list, HttpStatus.OK);

	}
}
	
	
	