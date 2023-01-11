package com.project.TunaProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.repository.MemberRepository;
import com.project.TunaProject.session.SessionVar;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AjaxController {
	
	private final MemberRepository memberRepository;
	
	@GetMapping("/joongbok")
	public String joongbok() {

		return "test/joongbok";
	}
	
	@PostMapping("/joongbok")
	@ResponseBody
	public int idCheck(@RequestParam("email") String email) {
		
		int cnt = memberRepository.idCheck(email);
		return cnt;
		
	}
}
