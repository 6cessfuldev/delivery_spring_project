package com.ezen.delivery.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.delivery.Handler.FileHandler;
import com.ezen.delivery.domain.DinerDTO;
import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.FileVO;
import com.ezen.delivery.domain.FoodDTO;
import com.ezen.delivery.domain.FoodVO;
import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.service.DinerService;
import com.ezen.delivery.service.FoodService;
import com.ezen.delivery.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/*")
public class AdminController {

	@Inject
	private DinerService dsv;
	
	@Inject
	private FoodService fsv;
	
	@Inject
	private FileHandler fhd;
	
	@Inject
	private UserService usv;
	
	
	@GetMapping("/")
	public String main() {
		return "admin/main";
	}
	
	@GetMapping("/user")
	public void getUser(Model model) {
		List<UserVO> list = usv.getUserList();
		model.addAttribute("list", list);
	}
	
	@GetMapping("/user/detail")
	public String userRegister(String user_id, Model model) {
		UserVO uvo = usv.getUserByID(user_id);
		model.addAttribute("user", uvo);
		return "/admin/user/detail";
	}
	
	@GetMapping("/user/register")
	public void userRegister() {}
	
//	@PostMapping("/user/insert")
//	public String userInsert(UserVO uvo) {
//		
//	}
	
	
	
	
	
	@GetMapping("/diner")
	public void diner(Model model) {
		List<DinerVO> list = dsv.getList();
		log.info(list.size()+"");
		model.addAttribute("list", list);
	}
	
	@GetMapping("/diner/register")
	public void dinerRegister() {}

	@PostMapping("/diner/insert")
	public String dinerInsert(DinerVO dvo, @RequestParam List<String> category, @RequestParam(value="file", required=false) MultipartFile file ) {
		
		DinerDTO ddto = new DinerDTO();
		
		String diner_category = "";
		
		for (String string : category) {
			diner_category+=string;
		}
		dvo.setDiner_category(diner_category);
		
		ddto.setDvo(dvo);
		
		if(file!=null) {
			FileVO fvo = fhd.uploadFiles(file);
			ddto.setFivo(fvo);
		}else {
			log.info("file is null");
		}
		
		int isOk = dsv.register(ddto);
		log.info(dvo.toString());
		
		return "admin/diner/register";
	}
	
	@GetMapping("/diner/detail")
	public String dinerDetail(int diner_code, Model model) {
		log.info("diner detail "+diner_code);
		DinerDTO ddto = dsv.getDiner(diner_code);
		
		DinerVO dvo = ddto.getDvo();
		FileVO fivo = ddto.getFivo();
		
		model.addAttribute("diner", dvo);
		model.addAttribute("file", fivo);
		
		List<FoodDTO> list = fsv.getListByDinerCode(diner_code);
		model.addAttribute("foodList", list);
	
		return "/admin/diner/detail";
	}
	
	@GetMapping("/diner/modify")
	public void dinerModify(int diner_code, Model model) {
		DinerDTO ddto = dsv.getDiner(diner_code);
		log.info(ddto.getDvo().toString());
		model.addAttribute("diner", ddto.getDvo());
		model.addAttribute("file", ddto.getFivo());
	}
	
	@PostMapping("/diner/update")
	public String dinerUpdate(DinerVO dvo, @RequestParam List<String> category, @RequestParam(value="file", required=false) MultipartFile file, Model model) {
		
		int isUp = 1;
		
		DinerDTO ddto = new DinerDTO();
		
		//입력된 파일이 있는 경우
		if(file != null) {
				
			//DB에 있는 기존 파일 정보
			FileVO fivo = dsv.getDiner(dvo.getDiner_code()).getFivo();
			
			//DB에 이미 정보가 있다면 저장소에서 이미지 파일 삭제
			if(fivo != null) {
				isUp *= fhd.deleteFile(fivo);
				
			}
			//새로 입력받은 이미지 파일을 저장소에 등록			
			fivo = fhd.uploadFiles(file);
			//기존에 사용하던 파일 코드가 있다면 재사용
			if(dvo.getDiner_file_code() != 0) {
				fivo.setFile_code(dvo.getDiner_file_code());				
			}
			
			ddto.setFivo(fivo);
		}
		
		String diner_category = "";
		for (String string : category) {
			diner_category+=string;
		}
		dvo.setDiner_category(diner_category);
		
		ddto.setDvo(dvo);
		
		//db 수정
		isUp *=  dsv.update(ddto);
		
		log.info(dvo.toString());
		
		model.addAttribute("diner", dsv.getDiner(dvo.getDiner_code()).getDvo());
		model.addAttribute("file", dsv.getDiner(dvo.getDiner_code()).getFivo());
		
		return "admin/diner/detail";	
	}
	
	@GetMapping("/diner/remove")
	public String dinerRemove(DinerVO dvo) {
		int isOk = dsv.remove(dvo.getDiner_code());
		
		return "redirect:/admin/diner";
	}
	
	@GetMapping("/food/register")
	public String foodRegister(int diner_code, Model model) {
		DinerVO dvo = dsv.getDiner(diner_code).getDvo();
		model.addAttribute("diner", dvo);
		
		return "admin/food/register";
	}
	
	@PostMapping("food/insert")
	public String foodInsert(FoodVO fvo, @RequestParam(value="file" ,required =false)MultipartFile file, Model model) {

		FoodDTO fdto = new FoodDTO();
		
		if(file != null) {
			
			FileVO fivo = fhd.uploadFiles(file); 
			fdto.setFilevo(fivo);
		}else {
			log.info("file is Null");
		}
		
		fdto.setFoodvo(fvo);
		
		int isOk = fsv.register(fdto);
		log.info("insert food "+(isOk>0?"success":"fail"));
			
		return dinerDetail(fvo.getFood_diner_code(), model);
	}
	
	@GetMapping("food/detail")
	public String foodDetail(int food_code, Model model) {
		
		FoodDTO fdto = fsv.getDetail(food_code);
		
		model.addAttribute("file", fdto.getFilevo());
		model.addAttribute("food", fdto.getFoodvo());
		
		return "/admin/food/detail";
	}

	@GetMapping("/food/modify")
	public void foodModify(int food_code, Model model) {
		FoodVO fvo = fsv.getFood(food_code);
		log.info(fvo.toString());
		model.addAttribute("food", fvo);
	}
	
	@GetMapping("/food/update")
	public String foodUpdate(FoodVO fvo, Model model) {
		int isUp = fsv.update(fvo);
		
		log.info("update "+(isUp>0 ? "success":"fail"));
		
		fvo = fsv.getFood(fvo.getFood_code());
		
		model.addAttribute("food", fvo);
		
		return "/admin/food/detail";
	}
	
	@GetMapping("/food/remove")
	public String foodRemove(int food_code, RedirectAttributes rttr) {
		FoodVO fvo = fsv.getFood(food_code);
		int isOk = fsv.remove(food_code);
		log.info("remove food "+(isOk>0?"success":"fail"));

		rttr.addAttribute("diner_code", fvo.getFood_diner_code());
		
		return "redirect:/admin/diner/detail";
	}
	
	
	
}
