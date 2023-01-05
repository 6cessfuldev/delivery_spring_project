package com.ezen.delivery.controller;

import java.io.File;
import java.util.List;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.delivery.domain.UserVO;
import com.ezen.delivery.service.UserService;
import com.google.common.net.MediaType;

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
		String setFrom = "myeonk@naver.com";
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

		String subject = "안녕하세요 test 메일 입니다 :-)";
		String content = "메일 테스트 내용"
				+ "<img src=\"https://www.google.com/imgres?imgurl=https%3A%2F%2Fimg1.daumcdn.net%2Fthumb%2FR1280x0.fjpg%2F%3Ffname%3Dhttp%3A%2F%2Ft1.daumcdn.net%2Fbrunch%2Fservice%2Fuser%2F32E9%2Fimage%2FBA2Qyx3O2oTyEOsXe2ZtE8cRqGk.JPG&imgrefurl=https%3A%2F%2Fbrunch.co.kr%2F%40happying%2F66&tbnid=uBQ8cebvR-okYM&vet=12ahUKEwi1hJjYoav8AhWOOpQKHdX0BeoQMygNegUIARD3AQ..i&docid=0RhOIZ63_Xb9-M&w=960&h=640&q=%EA%B0%95%EC%95%84%EC%A7%80&ved=2ahUKEwi1hJjYoav8AhWOOpQKHdX0BeoQMygNegUIARD3AQ\">";
		String from = "myeonk@naver.com";
		String to = "myeonk@naver.com";

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
	public void signUpGet() {}

	@PostMapping("/signup")
	public String signUpPost(Model model, UserVO uvo) {
		log.info(uvo.toString());
		boolean isUp = usv.signUp(uvo);
		if (isUp) {
			return "/member/login";
		} else {
			model.addAttribute("msg", "0");
			return "/member/signup";
		}
	}
	
	// 로그인
	
	@GetMapping("/login")
	public void loginGet() {}

	@PostMapping("/login")
	public String loginPost(Model model, String user_id, String user_pw, HttpServletRequest req) {
		log.info(">>> user_id : " + user_id + " >>> user_pw : " + user_pw);
		UserVO isUser = usv.isUser(user_id, user_pw);

		if (isUser != null) {
			HttpSession session = req.getSession();
			session.setAttribute("ses", isUser);

			model.addAttribute("user", isUser);
			model.addAttribute("msg", "1");
			return "redirect:/";
		} else {
			model.addAttribute("msg", "0");
			return "/member/login";
		}
	}
	
	// 로그아웃
	
	@GetMapping("/logout")
	public String logoutGet(Model model, HttpServletRequest req) {
		req.getSession().removeAttribute("ses");
		req.getSession().invalidate();
		return "redirect:/";
	}

	// 아이디 찾기
	
	@GetMapping("/find_id")
	public void findIdGet() {}
	
	@PostMapping(value="/find_id")
	public ResponseEntity<String> findId(String user_email){
		log.info(user_email);
		
		UserVO user = usv.getId(user_email);
		log.info("" + user);
		
		return (user.getUser_id() != null ? new ResponseEntity<String>(user.getUser_id(), HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	// 비밀번호 찾기
	
	@GetMapping("/find_pw")
	public void findPwGet() {}
	
	// 비밀번호 변경
	
	@GetMapping("/update_pw")
	public void updatePwGet() {}
	
	@PostMapping("/update_pw")
	public ResponseEntity<String> updatePw(String getEmail, String new_pw){
		log.info(getEmail);
		log.info(new_pw);
			
		int user = usv.updatePw(getEmail, new_pw);
		log.info("" + user);
		return (user > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	// 누가 썼어
	
	@GetMapping("/order")
	public void orderGet() {
	}

	// 아이디 중복 확인
	
	@PostMapping("/userIdCheck")
	public ResponseEntity<String> userIdCheck(String user_id){
		log.info(user_id);
		
		int existed = usv.isExist(user_id);
		log.info("" + existed);
		
		return (existed > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.OK));
							
	}
}
