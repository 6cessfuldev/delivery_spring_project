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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.security.oauth2.PrincipalDetails;
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
	public String upload(@RequestParam(value ="file", required=false) MultipartFile[] files,@RequestParam("diner_code") int diner_code, ReviewVO rvo, Authentication authentication) {
		
		log.info(rvo.toString());
		
		ReviewDTO ridto = new ReviewDTO();		
		if(files != null && files.length > 0) {
			List<ReviewImgVO> list = rihd.uploadFiles(files);
			ridto.setFList(list);
		}
				
		ridto.setRvo(rvo);		
		int isOk = rsv.insert(ridto,diner_code);
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
	
	
	//사장님댓글
//	@PatchMapping("/bossComment") //int review_order_code,
//	public ResponseEntity<String> bossComment(int diner_code,  String review_boss_comment){
//		String reviewContent = rsv.bossComment(diner_code, review_boss_comment);
//		return ResponseEntity.ok().body(reviewContent);
//	}
	

	//삭제
	@DeleteMapping(value="/delete/{review_code}", produces = {MediaType.TEXT_PLAIN_VALUE})
	   public ResponseEntity<String> remove(@PathVariable("review_code")int review_code){
	      log.info("review remove : "+review_code);
	      int isUp = rsv.remove(review_code);
	      log.info("remove isUp : " + (isUp>0?"ok":"fail"));
	      ReviewImgVO rivo = rsv.selectFile(review_code); 
	      int isOk = rsv.deleteFile(review_code);
	      isOk *= rihd.deleteFile(rivo);
	      return isUp>0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
