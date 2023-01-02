package com.project.TunaProject.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.domain.Notify;
import com.project.TunaProject.repository.mybatis.MybatisNotifyRepository;
import com.project.TunaProject.session.SessionVar;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/notify")
public class NotifyController {

	private final MybatisNotifyRepository mybatisNotifyRepository;
	
	@PostMapping("/post")
	public String notifyPost(@RequestParam Map<String, String> data, HttpServletRequest req) {
		
//		int cnt = mybatisNotifyRepository.selectById();
//		if(cnt > 0) {
//			return 1;
//		} else {
		
		HttpSession session = req.getSession(false);
		MemberVO tempVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);
		
		Notify n = new Notify();
		n.setNotifyType(data.get("postCode"));
		n.setNotifyMemberCode(data.get("pMemCode"));
		n.setDoNotifyMemberCode(String.valueOf(tempVO.getMemberCode()));
		n.setNotifyTarget(1);
		mybatisNotifyRepository.insertNotify(n);
//		
//		return 2;
//		}
		return "tuna:/";
	}
	
//	@PostMapping("/chat")
//	public String notifyChat(@RequestParam Map<String, String> postCode) {
//		
//		String a = postCode.get("postCode");
//		System.out.println("****" + a + "****");
//		
//		return "redirect:/";
//	}
}
