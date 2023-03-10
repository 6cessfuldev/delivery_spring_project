package com.ezen.delivery.controller;

import java.io.File;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.security.oauth2.PrincipalDetails;
import com.ezen.delivery.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {

	@Inject
	private UserService usv;
	
	@Autowired
	private JavaMailSender mailSender;	
	
	// 이메일 인증
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {

		// 뷰(view)로부터 넘어온 데이터 확인
		log.info("이메일 데이터 전송 확인");
		log.info("인증번호 : " + email);

		// 인증번호 생성
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		log.info("인증번호 : " + checkNum);

		// 이메일 보내기
		String setFrom = "yukssungmin@naver.com";
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content = "<먹어요> 홈페이지를 방문해주셔서 감사합니다." + "<br><br>" + "인증 번호는 " + checkNum + "입니다." + "<br>"
				+ "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String num = Integer.toString(checkNum);

		return num;

	}

	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public void sendMailTest() throws Exception {

		String subject = "안녕하세요 <먹어요> test 메일 입니다.";
		String content = "메일 테스트 내용"
				+ "<img src=\"https://www.google.com/imgres?imgurl=https%3A%2F%2Fimg1.daumcdn.net%2Fthumb%2FR1280x0.fjpg%2F%3Ffname%3Dhttp%3A%2F%2Ft1.daumcdn.net%2Fbrunch%2Fservice%2Fuser%2F32E9%2Fimage%2FBA2Qyx3O2oTyEOsXe2ZtE8cRqGk.JPG&imgrefurl=https%3A%2F%2Fbrunch.co.kr%2F%40happying%2F66&tbnid=uBQ8cebvR-okYM&vet=12ahUKEwi1hJjYoav8AhWOOpQKHdX0BeoQMygNegUIARD3AQ..i&docid=0RhOIZ63_Xb9-M&w=960&h=640&q=%EA%B0%95%EC%95%84%EC%A7%80&ved=2ahUKEwi1hJjYoav8AhWOOpQKHdX0BeoQMygNegUIARD3AQ\">";
		String from = "aleod1007@naver.com";
		String to = "aleod1007@naver.com";

		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");

			mailHelper.setFrom(from);
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content, true);

			FileSystemResource file = new FileSystemResource(new File("C:\\Users\\ezen\\Desktop\\logo.png"));
			mailHelper.addAttachment("logo.png", file);

			mailSender.send(mail);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 회원가입

	@GetMapping("/signup")
	public void signUpGet() {

		log.info("회원가입");

	}

	@PostMapping("/signup")
	public String signUpPost(Model model, UserVO uvo) {
		log.info(uvo.toString());
		uvo.setUser_Role("USER");
		boolean isUp = usv.signUp(uvo);
		if (isUp) {
			return "/member/login";
		} else {
			model.addAttribute("msg", "0");
			return "/member/signup";
		}
	}

	// 이메일 중복 확인

	@PostMapping("/userEmailCheck")
	public ResponseEntity<String> userEmailCheck(String user_email) {
		log.info(user_email);

		int emailExisted = usv.emailExist(user_email);
		log.info("" + emailExisted);

		return (emailExisted > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.OK));
	}

	// 아이디 중복 확인

	@PostMapping("/userIdCheck")
	public ResponseEntity<String> userIdCheck(String user_id) {
		log.info(user_id);

		int existed = usv.isExist(user_id);
		log.info("" + existed);

		return (existed > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.OK));
	}

	// 로그인
	@GetMapping("/login")
	public void loginGet(Model model) {}
	

// 	스프링 시큐리티 formlogin과 CustomLoginSuccessHandler, CustomLoginFailureHandler으로 기능 이전 
//	@PostMapping("/login")
//	public String loginPost(Model model, String user_id, String user_pw, HttpServletRequest req) {
//		log.info(">>> user_id : " + user_id + " >>> user_pw : " + user_pw);
//		UserVO isUser = usv.isUser(user_id, user_pw);
//
//		if (isUser != null) { // 로그인 성공
//			HttpSession session = req.getSession();
//			session.setAttribute("user", isUser);
//			model.addAttribute("user", isUser);
//			int isOk = usv.loginDate(user_id);
//			log.info(">>> update login date " + (isOk > 0 ? "Ok" : "Fail"));
//
//			return "redirect:/";
//
//		} else { // 로그인 실패
//			model.addAttribute("msg", "0");
//			int isOk = usv.loginFailCnt(user_id);
//			log.info(">>> add fail Count " + (isOk > 0 ? "Ok" : "Fail"));
//
//			return "/member/login";
//		}
//	}

	// 네이버 로그인

	@GetMapping("/callback")
	public void callback() {
	}
	
//	@PostMapping("/naverLogin")
//	public String naverLoginPost(String accessToken, Model model, HttpServletRequest req) {
//
//		UserVO naverUser = ApiMemberProfile.getProfile(accessToken);
//		UserVO getUser = usv.getUserByID(naverUser.getUser_id());
//
//		if (getUser == null) { // 미가입 회원인 경우
//
//			boolean isUp = usv.naverSignUp(naverUser);
//
//			if (isUp) {
//				HttpSession session = req.getSession();
//				session.setAttribute("user", naverUser);
//				model.addAttribute("user", naverUser);
//				int isOk = usv.loginDate(naverUser.getUser_id());
//				log.info(">>> update login date " + (isOk > 0 ? "Ok" : "Fail"));
//			}
//
//		} else { // 이미 가입한 회원인 경우
//			HttpSession session = req.getSession();
//			session.setAttribute("user", getUser);
//			model.addAttribute("user", getUser);
//			int isOk = usv.loginDate(getUser.getUser_id());
//			log.info(">>> update login date " + (isOk > 0 ? "Ok" : "Fail"));
//		}
//
//		return "/member/naverLogin";
//	}

	// 회원 정보

	@GetMapping({ "/detail_userInfo", "/modify_userInfo" })
	public void userInfo(Authentication authentication, Model model) {
		log.info(authentication.toString());
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		String user_id = principalDetails.getUsername();
		log.info(principalDetails.getUser().toString());
		int isOauth = 0;
		if(principalDetails.getOAuth2UserInfo()!=null) {
			isOauth = 1;
		}
		
		UserVO uvo = usv.getUserByID(user_id);
		model.addAttribute("user", uvo);
		model.addAttribute("isOauth", isOauth);
	}

	// 회원 정보 수정

	@PostMapping("/modify_userInfo")
	public ResponseEntity<String> modifyUserInfo(String user_id, String new_pw, String new_phone, HttpSession session) {
		log.info(user_id);
		log.info(new_pw);
		log.info(new_phone);

		// DB 변경
		int modUser = usv.modifyUserInfo(user_id, new_pw, new_phone, session);
		log.info("" + modUser);
		return (modUser > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	// 회원 탈퇴

	@PostMapping("/remove_userInfo")
	public ResponseEntity<String> removeUserInfo(String user_id, HttpServletRequest req) {
		log.info(user_id);

		req.getSession().removeAttribute("user");
		req.getSession().invalidate();

		int delUser = usv.removeUserInfo(user_id);
		log.info("" + delUser);

		return (delUser > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	// 로그아웃
//	@GetMapping("/logout")
//	public String logoutGet(HttpServletRequest req, HttpSession session) {
//
//		UserVO uvo = (UserVO) session.getAttribute("user");
//		int isOk = usv.logoutDate(uvo.getUser_id());
//		log.info(">>> update logout date " + (isOk > 0 ? "Ok" : "Fail"));
//
//		req.getSession().removeAttribute("user");
//		req.getSession().invalidate();
//
//		return "redirect:/";
//	}

	// 아이디 찾기

	@GetMapping("/find_id")
	public void findIdGet() {
	}

	@PostMapping(value = "/find_id")
	public ResponseEntity<String> findId(String user_email) {
		log.info(user_email);

		UserVO user = usv.getId(user_email);
		log.info("" + user);

		return (user.getUser_id() != null ? new ResponseEntity<String>(user.getUser_id(), HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	// 비밀번호 찾기

	@GetMapping("/find_pw")
	public void findPwGet() {
	}

	// 비밀번호 변경

	@GetMapping("/update_pw")
	public void updatePwGet() {
	}

	@PostMapping("/update_pw")
	public ResponseEntity<String> updatePw(String getEmail, String new_pw) {
		log.info(getEmail);
		log.info(new_pw);

		int isEqual = usv.updatePw(getEmail, new_pw);
		log.info("" + isEqual);
		return (isEqual > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.OK));
	}

}
