package com.project.TunaProject.controller;


import org.springframework.boot.autoconfigure.cassandra.CassandraProperties.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.repository.MemberRepository;
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
		
		MemberVO memberVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);
		
		model.addAttribute("memberVO",memberVO);
		
		return "myPage/memberUpdate";
	}
	//비밀번호 변경	
	@GetMapping("/myPage/passwordUpdate")
	public String passwordUpdate() {
		return "myPage/passwordUpdate";
	}
	//회원 탈퇴
	@GetMapping("/myPage/memberOut")
	public String memberOut() {
		return "myPage/memberOut";
	}
}
