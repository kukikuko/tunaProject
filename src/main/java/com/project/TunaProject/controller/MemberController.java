package com.project.TunaProject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
	
	
	private final MemberRepository memberRepository;
	
	@GetMapping("/join")
	public String join() {
		return "join/join";
	}
	
	@PostMapping("/join")
	public String memberInsert(@ModelAttribute MemberVO memberVO) {
		
		
		memberVO.setMemberMail(memberVO.getMemberMail1()+"@"+memberVO.getMemberMail2());
		memberVO.setMemberPN(String.valueOf(memberVO.getMemberPN1())+String.valueOf(memberVO.getMemberPN2()));
		memberRepository.memberInsert(memberVO);
		
		log.info("member {}", memberVO);
		return "redirect:/";
	}
	
}
