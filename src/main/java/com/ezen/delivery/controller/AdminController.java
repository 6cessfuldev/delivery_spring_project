package com.ezen.delivery.controller;

import java.util.ArrayList;
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
import com.ezen.delivery.Handler.PagingHandler;
import com.ezen.delivery.domain.AdminPagingVO;
import com.ezen.delivery.domain.ChoiceVO;
import com.ezen.delivery.domain.DinerDTO;
import com.ezen.delivery.domain.DinerVO;
import com.ezen.delivery.domain.FileVO;
import com.ezen.delivery.domain.FoodDTO;
import com.ezen.delivery.domain.FoodVO;
import com.ezen.delivery.domain.LoginDTO;
import com.ezen.delivery.domain.LoginVO;
import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.service.ChoiceService;
import com.ezen.delivery.service.DinerService;
import com.ezen.delivery.service.FoodService;
import com.ezen.delivery.service.LoginService;
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

	@Inject
	private ChoiceService csv;

	@GetMapping("/")
	public String main() {
		return "admin/main";
	}

	@GetMapping("/diner")
	public void diner(Model model, AdminPagingVO pgvo) {
		int totalCount = dsv.totalCount();
		PagingHandler pgh = new PagingHandler(pgvo, totalCount);
		model.addAttribute("pgh", pgh);
		List<DinerVO> list = dsv.getListwithAdminPaging(pgvo);
		model.addAttribute("list", list);
	}

	@GetMapping("/diner/register")
	public void dinerRegister() {
	}

	@PostMapping("/diner/insert")
	public String dinerInsert(DinerVO dvo, @RequestParam(value = "category", required = false) List<String> category,
			@RequestParam(value = "file", required = false) MultipartFile file) {

		DinerDTO ddto = new DinerDTO();

		String diner_category = "";

		for (String string : category) {
			diner_category += string;
		}
		dvo.setDiner_category(diner_category);

		ddto.setDvo(dvo);

		log.info(dvo.toString());

		if (file != null) {
			FileVO fvo = fhd.uploadFiles(file);
			ddto.setFivo(fvo);
		} else {
			log.info("file is null");
		}

		int isOk = dsv.register(ddto);
		log.info(dvo.toString());

		return "admin/diner/register";
	}

	@GetMapping("/diner/detail")
	public String dinerDetail(int diner_code, AdminPagingVO pgvo, Model model) {
		
		log.info("diner detail " + diner_code);
		DinerDTO ddto = dsv.getDiner(diner_code);
		
		DinerVO dvo = ddto.getDvo();
		FileVO fivo = ddto.getFivo();
		
		model.addAttribute("diner", dvo);
		model.addAttribute("file", fivo);

		int totalCount = fsv.totalCount(diner_code);
		PagingHandler pgh = new PagingHandler(pgvo, totalCount);
		model.addAttribute("pgh", pgh);

		List<FoodDTO> list = fsv.getListByDinerCodeWithPaging(diner_code, pgvo);
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
	public String dinerUpdate(DinerVO dvo, @RequestParam List<String> category,
			@RequestParam(value = "file", required = false) MultipartFile file, Model model) {

		int isUp = 1;

		DinerDTO ddto = new DinerDTO();

		// 입력된 파일이 있는 경우
		if (file != null) {

			// DB에 있는 기존 파일 정보
			FileVO fivo = dsv.getDiner(dvo.getDiner_code()).getFivo();

			// DB에 이미 정보가 있다면 저장소에서 이미지 파일 삭제
			if (fivo != null) {
				isUp *= fhd.deleteFile(fivo);

			}
			// 새로 입력받은 이미지 파일을 저장소에 등록
			fivo = fhd.uploadFiles(file);
			// 기존에 사용하던 파일 코드가 있다면 재사용
			if (dvo.getFile_code() != 0) {
				fivo.setFile_code(dvo.getFile_code());
			}

			ddto.setFivo(fivo);
		}

		String diner_category = "";
		for (String string : category) {
			diner_category += string;
		}
		dvo.setDiner_category(diner_category);

		ddto.setDvo(dvo);

		// db 수정
		isUp *= dsv.update(ddto);

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
	public String foodInsert(FoodVO fvo, @RequestParam(value = "file", required = false) MultipartFile file,
			Model model) {

		FoodDTO fdto = new FoodDTO();

		if (file != null) {

			FileVO fivo = fhd.uploadFiles(file);
			fdto.setFilevo(fivo);
		} else {
			log.info("file is Null");
		}

		fdto.setFoodvo(fvo);

		int isOk = fsv.register(fdto);
		log.info("insert food " + (isOk > 0 ? "success" : "fail"));

		return "redirect:/diner/detail?diner_code="+fvo.getDiner_code();
	}

	@GetMapping("food/detail")
	public String foodDetail(int food_code, Model model) {

		FoodDTO fdto = fsv.getDetail(food_code);

		model.addAttribute("file", fdto.getFilevo());
		model.addAttribute("food", fdto.getFoodvo());

		List<ChoiceVO> list = csv.getList(food_code);

		model.addAttribute("choiceList", list);

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

		log.info("update " + (isUp > 0 ? "success" : "fail"));

		fvo = fsv.getFood(fvo.getFood_code());

		model.addAttribute("food", fvo);

		return "/admin/food/detail";
	}

	@GetMapping("/food/remove")
	public String foodRemove(int food_code, RedirectAttributes rttr) {
		FoodVO fvo = fsv.getFood(food_code);
		int isOk = fsv.remove(food_code);
		log.info("remove food " + (isOk > 0 ? "success" : "fail"));

		rttr.addAttribute("diner_code", fvo.getDiner_code());

		return "redirect:/admin/diner/detail";
	}

	@GetMapping("/choice/register")
	public String choiceRegister(int food_code, Model model) {
		FoodVO fvo = fsv.getFood(food_code);
		model.addAttribute("food", fvo);

		return "admin/choice/register";
	}

	@PostMapping("/choice/insert")
	public String choiceInsert(ChoiceVO cvo, RedirectAttributes reAttr) {
		int isOk = csv.register(cvo);
		log.info(">>> choice insert " + (isOk > 0 ? "Ok" : "Fail"));
		reAttr.addAttribute("food_code", cvo.getFood_code());

		return "redirect:/admin/food/detail";
	}

	@GetMapping("/choice/modify")
	public void choiceModify(int choice_code, Model model) {
		ChoiceVO cvo = csv.getChoice(choice_code);
		FoodVO fvo = fsv.getFood(cvo.getFood_code());
		model.addAttribute("choice", cvo);
		model.addAttribute("food", fvo);
	}

	@GetMapping("/choice/update")
	public String choiceUpdate(ChoiceVO cvo, RedirectAttributes reAttr) {
		int isOk = csv.update(cvo);
		log.info(">>> choice update " + (isOk > 0 ? "Ok" : "Fail"));
		reAttr.addAttribute("food_code", cvo.getFood_code());

		return "redirect:/admin/food/detail";
	}

	@GetMapping("/choice/remove")
	public String choiceRemove(int choice_code, RedirectAttributes reAttr) {
		ChoiceVO cvo = csv.getChoice(choice_code);
		int isOk = csv.remove(choice_code);
		log.info(">>> choice remove " + (isOk > 0 ? "Ok" : "Fail"));
		reAttr.addAttribute("food_code", cvo.getFood_code());

		return "redirect:/admin/food/detail";
	}

	@GetMapping("/user")
	public void getUser(Model model) {
		List<UserVO> userList = usv.getUserList();
		model.addAttribute("userList", userList);
	}

	@GetMapping("/user/register")
	public void userRegiseter() {
	}

	@GetMapping("/user/insert")
	public String userInsert(UserVO uvo) {
		boolean isOk = usv.signUp(uvo);
		log.info(">>> admin user insert " + (isOk ? "Ok" : "Fail"));
		return "redirect:/admin/user";
	}

	@GetMapping("/user/detail")
	public String userDetail(String user_id, Model model) {
		UserVO uvo = usv.getUserByID(user_id);
		model.addAttribute("user", uvo);

		return "/admin/user/detail";
	}

	@GetMapping("/user/modify")
	public void userModify(String user_id, Model model) {
		UserVO uvo = usv.getUserByID(user_id);
		model.addAttribute("user", uvo);
	}

	@GetMapping("/user/update")
	public String userUpdate(UserVO uvo, RedirectAttributes reAttr) {
		int isOk = usv.modifyUserInfo(uvo);
		log.info(">>> user update " + (isOk > 0 ? "Ok" : "Fail"));
		reAttr.addAttribute("user_id", uvo.getUser_id());

		return "redirect:/admin/user/detail";
	}

	@GetMapping("/user/remove")
	public String userRemove(String user_id) {
		int isOk = usv.removeUserInfo(user_id);
		log.info(">>> user remove " + (isOk > 0 ? "Ok" : "Fail"));

		return "redirect:/admin/user";
	}
  
     @GetMapping("/loginHistory")
   public void getloginHistory(Model model) {
	  
	  List<LoginDTO> ldtoList = new ArrayList<LoginDTO>();
      
	  List<LoginVO> loginList = lsv.getLoginList();

      for(int i=0; i<loginList.size(); i++) {
    	  UserVO user = usv.getUserByID(loginList.get(i).getUser_id());
    	  
      }
      
      model.addAttribute("loginHistory", ldtoList);
   }

}