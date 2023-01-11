package com.ezen.delivery.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.delivery.Handler.ReviewImgHandler;
import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewImgVO;
import com.ezen.delivery.domain.ReviewVO;
import com.ezen.delivery.service.DinerService;
import com.ezen.delivery.service.ReviewService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller

@RequestMapping("/review/*")
public class ReviewController {
	
	@Inject
	private DinerService dsv;
	@Inject
	private ReviewImgHandler rihd;
	@Inject
	private ReviewService rsv;
	
	
	@PostMapping(value="/upload")
	@ResponseBody
//	public String upload(@RequestParam(value ="file", required=false) MultipartFile file, 
	public String upload(@RequestParam("file") MultipartFile[] files, 
			 ReviewVO rvo, HttpSession session) {
		ReviewDTO ridto = new ReviewDTO();		
		if(files != null && files.length > 0) {
			List<ReviewImgVO> list = rihd.uploadFiles(files);
			ridto.setFList(list);
		}		
//		UserVO uvo = (UserVO)session.getAttribute("user");
//		ReviewVO rvo = new ReviewVO();
//		rvo.setReview_diner_code(review_diner_code);
//		rvo.setReview_user_id("user_id");
//		rvo.setReview_content(review_content);
		ridto.setRvo(rvo);		
		int isOk = rsv.insert(ridto);	
		log.info("fList : "+ridto.getFList());
		log.info("rvo : "+ridto.getRvo());
		return "Success";
	}

	
	@GetMapping(value ="/list/{diner_code}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ReviewDTO>> spread(@PathVariable("diner_code")int diner_code){
		log.info("diner_code : "+diner_code);
		List<ReviewDTO> list = rsv.getList(diner_code);
		return new ResponseEntity<List<ReviewDTO>>(list,HttpStatus.OK);
	}
	
	
	
	
}