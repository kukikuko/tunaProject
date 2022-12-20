package com.project.TunaProject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.form.LoginForm;
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
	
	@GetMapping("/login")
	public String login(Model model) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		
		return "login/login";
	}
	
	@PostMapping("/login_cookie")
	public String doLogin_cookie(@ModelAttribute LoginForm loginForm,
			BindingResult bindingResult, HttpServletResponse resp) {
		log.info("loginForm {}", loginForm);
		
		validateLoginForm(loginForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "login/login";
		}
		
	 	MemberVO memberVO = loginService.login(loginForm.getEmail(), loginForm.getPassword());

	 	log.info("login {}", memberVO);
	 	
	 	if(memberVO == null) { //계정정보가 없거나, 비밀번호가 안맞거나 로그인 실패
	 		bindingResult.reject("loginForm", "아이디 or 비밀번호 불일치");
	 		return "login/login";
	 	}
		
	 	//정상적으로 로그인 처리가 된 경우
	 	
	 	//쿠키를 추가
	 	Cookie cookie = new Cookie("email", memberVO.getMemberMail1());
	 	Cookie cookie2 = new Cookie("email", memberVO.getMemberMail1().toString());
	 	resp.addCookie(cookie);
	 	resp.addCookie(cookie2);
		
		return "redirect:/";
	}
	
	@PostMapping("/login_Session")
	public String doLogin_Session(@ModelAttribute LoginForm loginForm,
			BindingResult bindingResult, HttpServletResponse resp) {
		log.info("loginForm {}", loginForm);
		
		validateLoginForm(loginForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "login/login";
		}
		
	 	MemberVO memberVO = loginService.login(loginForm.getEmail(), loginForm.getPassword());

	 	log.info("login {}", memberVO);
	 	
	 	if(memberVO == null) { //계정정보가 없거나, 비밀번호가 안맞거나 로그인 실패
	 		bindingResult.reject("loginForm", "아이디 or 비밀번호 불일치");
	 		return "login/login";
	 	}
		
	 	sessionManager.create(memberVO, resp);
		return "redirect:/";
	}
	
	@PostMapping("/login_old")
	public String doLogin_old(@ModelAttribute LoginForm loginForm,
			BindingResult bindingResult, HttpServletResponse resp
			, HttpServletRequest req) {
		log.info("loginForm {}", loginForm);
		
		validateLoginForm(loginForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "login/login";
		}
		
	 	MemberVO memberVO = loginService.login(loginForm.getEmail(), loginForm.getPassword());

	 	log.info("login {}", memberVO);
	 	
	 	if(memberVO == null) { //계정정보가 없거나, 비밀번호가 안맞거나 로그인 실패
	 		bindingResult.reject("loginForm", "아이디 or 비밀번호 불일치");
	 		return "login/login";
	 	}
		
	 	
	 	HttpSession session = req.getSession();
	 	session.setAttribute(SessionVar.LOGIN_MEMBER, memberVO);
		
		return "redirect:/";
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
			session.invalidate();
		}
		
		return "redirect:/";
	}
	
	@PostMapping("/logout_Session")
	public String logout_Session(HttpServletResponse resp, HttpServletRequest req) {
		sessionManager.remove(req);
		//세션 사용자쪽에 tempSessionId Cookie.
		//세션 주체 서버
		//서버 세션매니저 정보를 삭제
		
		return "redirect:/";
	}
	
	@PostMapping("/logout_cookie")
	public String logout_cookie(HttpServletResponse resp) {
		Cookie cookie = new Cookie("memberId", null);
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		
		return "redirect:/";
	}

	
}
