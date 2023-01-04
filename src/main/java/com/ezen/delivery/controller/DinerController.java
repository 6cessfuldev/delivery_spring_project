package com.ezen.delivery.controller;

import java.util.List;

import javax.inject.Inject;

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
import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.ReviewDTO;
import com.ezen.delivery.domain.ReviewImgVO;
import com.ezen.delivery.domain.ReviewVO;
import com.ezen.delivery.repository.UserDAO;
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
	@Inject
	private UserDAO userDao;

	
	@GetMapping("/list")
	public String list(Model model) {
		List<DinerVO> list = dsv.getList();
		model.addAttribute("list",list);
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
	
	
	