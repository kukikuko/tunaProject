package com.project.TunaProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.repository.MemberRepository;
import com.project.TunaProject.service.EmailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@org.springframework.stereotype.Controller
public class EmailController {

	private final EmailService emailService;
	private final MemberRepository memberRepository;

	@GetMapping("/emailConfirm")
	public String getemail() {
		return "confirm";
	}

	@PostMapping("/emailConfirm")
	@ResponseBody
	public String emailConfirm(@RequestParam String email) throws Exception {
		String confirm = emailService.sendSimpleMessage(email);
		return confirm;
	}

	//비밀번호 찾는 메소드
	@GetMapping("/emailFindPw")
	public String getEmail() {
		return "find";
	}

	@PostMapping("/emailFindPw")
	@ResponseBody
	public String emailFindPw(@RequestParam String email) throws Exception {
		String password = memberRepository.emailFindPw(email);
		System.out.println(email);
		String confirm = emailService.sendSimpleMessagePassword(email, password);
		return "redirect:/login";
	}

}