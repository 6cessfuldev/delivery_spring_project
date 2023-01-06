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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.delivery.Handler.FileHandler;
import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewImgVO;
import com.ezen.delivery.domain.ReviewVO;
import com.ezen.delivery.domain.UserVO;
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
	private FileHandler fhd;
	@Inject
	private ReviewService rsv;
	
	 

	//파일을 올리면 파일핸들러를 거쳐서? 디비에 들어가기?
//	@PostMapping("/review")
//	public String upload(HttpSession session, UserVO uvo, ) {
//		
//		로그인을 한 회원만 작성 가능?
//		String userID = ((UserVO)session.getAttribute("ses")).getUser_id();
		//files에 값이 있으면 파일핸들러 uploadFiles 실행,,?
		
		//리뷰dto는,,,필요없나?
		
		//js에 리뷰랑 이미지를 한번에 등록하게,,,
		
		
		
//		if(files[0].getSize()>0) { 
//			fList = fhd.uploadFiles(files); 
//			log.info("file!");
//		}else {
//			log.info("file null"); 
//		} 
//		fList = fhd.uploadFiles(files);
//	}
//	
//	@postMapping(value="/post")
//	public ResponseEntity<String> post(@RequestBody )
	
	//리뷰(reviewService랑 연결)
	/*
	 * @PostMapping(value = "/post", consumes = "application/json", produces =
	 * {MediaType.TEXT_PLAIN_VALUE}) 
	 * public ResponseEntity<String> post(@RequestBody ReviewVO rvo){ 
	 * log.info("review post : "+rvo.toString()); 
	 * int isUp = rsv.register(rvo); 
	 * log.info("register isUp : "+ (isUp>0? "ok":"fail"));
	 * 
	 * return isUp > 0? new ResponseEntity<String>("1", HttpStatus.OK) : new
	 * ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 */
	//리뷰(reviewService랑 연결)
	
	@GetMapping(value="/{review_diner_code}",produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ReviewVO>> spread(@PathVariable("review_diner_code")int review_diner_code){
		log.info("Review List review_diner_code : "+review_diner_code);
		List<ReviewVO> list = rsv.getList(review_diner_code);
		return new ResponseEntity<List<ReviewVO>>(list,HttpStatus.OK);
	}

	
	
	//비동기로
	
	//이미지파일(서비스는 그대로 DinerService)
//	@PostMapping("/reviewfile") 
//	public String detailReview(ReviewVO rvo,RedirectAttributes rttr, @RequestParam(name="files", required = false) MultipartFile[] files) { 
//		List<ReviewImgVO> fList = null;
//		if(files[0].getSize()>0) { 
//			fList = fhd.uploadFiles(files); 
//			log.info("file!");
//		}else {
//			log.info("file null"); 
//		} 
//		ReviewDTO ddto = new ReviewDTO(rvo, fList); 
//		int isOk = dsv.reviewFile(new ReviewDTO(rvo, fList)); 
//		rttr.addAttribute("isOk", isOk>0? "1":"0"); 
//		log.info("reviewFile register >> "+ (isOk>0 ? "OK":"FAIL"));
//		return "/diner/detail"; 
//	}
	
}
