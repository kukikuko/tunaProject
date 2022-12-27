package com.project.TunaProject.controller;


import org.springframework.boot.autoconfigure.cassandra.CassandraProperties.Request;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.form.LoginForm;
import com.project.TunaProject.repository.MemberRepository;
import com.project.TunaProject.service.Tuna_LoginService;
import com.project.TunaProject.session.SessionManager;
import com.project.TunaProject.session.SessionVar;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final SessionManager sessionManager;
	private final MemberRepository memberRepository;
	private final Tuna_LoginService loginService;

	@GetMapping("/join")
	public String join() {
		return "join/join";
	}
	
	@PostMapping("/join")
	public String memberInsert(@ModelAttribute MemberVO memberVO) {
		
		
		memberVO.setMemberMail(memberVO.getMemberMail1()+"@"+memberVO.getMemberMail2());
		memberVO.setMemberPN(String.valueOf(memberVO.getMemberPN1())+"-"+String.valueOf(memberVO.getMemberPN2())+"-"+String.valueOf(memberVO.getMemberPN3()));
		memberRepository.memberInsert(memberVO);
		
		log.info("member {}", memberVO);
		return "redirect:/";
	}
	
	
	//개인정보 변경
	@GetMapping("/myPage/memberUpdate")
	public String memberUpdate(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		
		MemberVO tempVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);
		
		MemberVO memberVO = memberRepository.selectByEmail(tempVO.getMemberMail());
		
		model.addAttribute("memberVO",memberVO);
		log.info("updateGET member {}", memberVO);
		return "myPage/memberUpdate";
	}
	
	@PostMapping("/myPage/memberUpdate")
	public String updateMemberByEmail(MemberVO memberVO, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);

		MemberVO tempVO = (MemberVO)session.getAttribute(SessionVar.LOGIN_MEMBER);
		
		memberVO.setMemberPN(String.valueOf(memberVO.getMemberPN1())+String.valueOf(memberVO.getMemberPN2())+String.valueOf(memberVO.getMemberPN3()));
		memberVO.setMemberMail(tempVO.getMemberMail());
		log.info("update memberVO {}", memberVO);
		memberRepository.updateMemberByEmail(memberVO);
		
		return "redirect:/";
	}
	
	//비밀번호 변경	
	@GetMapping("/myPage/passwordUpdate")
	public String passwordUpdate(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		
		MemberVO tempVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);
		MemberVO memberVO = memberRepository.selectByEmail(tempVO.getMemberMail());
		memberVO.setMemberMail(tempVO.getMemberMail());
		model.addAttribute("memberVO",memberVO);
		log.info("updateGET member {}", memberVO);
		return "myPage/passwordUpdate";
	}

	
	//1225
	@PostMapping("/myPage/passwordUpdate")
	public String updatePassword(@ModelAttribute LoginForm loginForm,
			BindingResult bindingResult, HttpServletResponse resp
			, HttpServletRequest req, MemberVO memberVO
			, @RequestParam(name="redirectURL", defaultValue = "/") String redirectURL ) {
		
		HttpSession session = req.getSession(false);

		MemberVO tempVO = (MemberVO)session.getAttribute(SessionVar.LOGIN_MEMBER);
		memberVO.setMemberMail(tempVO.getMemberMail());
		
		log.info("update memberVO {}", memberVO);
		
		memberRepository.updatePassword(memberVO);
		
	 		return "redirect:/";
	}
	public void validateLoginForm(LoginForm loginForm, Errors errors) {
		if(!StringUtils.hasText(loginForm.getEmail())) {
			errors.rejectValue("loginId", null, "아이디 필수 입력입니다.");
		}
		
		if(!StringUtils.hasText(loginForm.getPassword())) {
			errors.rejectValue("password", null, "비밀번호 필수 입력입니다.");
		}
	}
	//회원 탈퇴
	@GetMapping("/myPage/memberOut")
	public String memberOut() {
		return "myPage/memberOut";
	}
}
