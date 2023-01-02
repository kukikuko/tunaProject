package com.project.TunaProject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.form.LoginForm;
import com.project.TunaProject.repository.MemberRepository;
import com.project.TunaProject.service.Tuna_LoginService;
import com.project.TunaProject.session.SessionManager;
import com.project.TunaProject.session.SessionVar;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final Tuna_LoginService loginService;
	private final SessionManager sessionManager;
	private final MemberRepository memberRepository;
	
	
	@GetMapping("/uuid_info/{uuid_v}")
	public String infobyuuid(@PathVariable("uuid_v") String uuid_v)
	{
		String result = "";
		result = memberRepository.selectByUUID(uuid_v).getMemberMail().toString();
		// 참여한 방목록 //해당 방의 사진 이미지 
		return result;
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		
		return "login/login";
	}
	

	
	@PostMapping("/login")
	public String doLogin(@ModelAttribute LoginForm loginForm,
			BindingResult bindingResult, HttpServletResponse resp
			, HttpServletRequest req
			, @RequestParam(name="redirectURL", defaultValue = "/") String redirectURL ) {
		log.info("loginForm {}", loginForm);
		
		validateLoginForm(loginForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "login/login";
		}
		
	 	MemberVO memberVO = loginService.login(loginForm.getEmail(), loginForm.getPassword());
		//Ctrl+Shift+o
	 	//Ctrl+1
	 	log.info("login {}", memberVO);
	 	
	 	if(memberVO == null) { //계정정보가 없거나, 비밀번호가 안맞거나 로그인 실패
	 		bindingResult.reject("loginForm", "아이디 or 비밀번호 불일치");
	 		return "login/login";
	 	}
		

	 	HttpSession session = req.getSession();
	 	session.setAttribute(SessionVar.LOGIN_MEMBER, memberVO);
	 	
	     memberVO.setActiveUUID(session.getId());
	     memberRepository.updateUUID(memberVO);

		
		return "redirect:" + redirectURL; //  /     /foods/new
	}
	
	
	
	
	public void validateLoginForm(LoginForm loginForm, Errors errors) {
		if(!StringUtils.hasText(loginForm.getEmail())) {
			errors.rejectValue("loginId", null, "아이디 필수 입력입니다.");
		}
		
		if(!StringUtils.hasText(loginForm.getPassword())) {
			errors.rejectValue("password", null, "비밀번호 필수 입력입니다.");
		}
	}
	
	@PostMapping("/logout")
	public String logout(HttpServletResponse resp, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		
		if(session != null) {
		 	MemberVO memberVO = (MemberVO)session.getAttribute(SessionVar.LOGIN_MEMBER);
		 	memberVO.setActiveUUID(null);
		     memberRepository.updateUUID(memberVO);
			session.invalidate();
		}
		
		return "redirect:/";
	}
	
	
	@GetMapping("/naverLogin")
	public String naver() {
		return "login/naverCallback";
	}
	
	@PostMapping("/naverLogin")
	public String naverLogin() {
		return "login/naverCallback";
	}
}
