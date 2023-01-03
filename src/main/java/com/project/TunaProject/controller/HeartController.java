package com.project.TunaProject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.TunaProject.domain.Heart;
import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.repository.HeartRepository;
import com.project.TunaProject.session.SessionVar;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HeartController {
	
	private final HeartRepository heartRepository;
	
	@PostMapping("/heart")
	@ResponseBody
	public Integer heart(@RequestParam String postCode, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		MemberVO tempVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);
		
		Heart h = new Heart();
		
		h.setHMemCode(Integer.toString(tempVO.getMemberCode()));
		h.setHPostCode(postCode);
		
		int cnt = heartRepository.countHeart(h);
		
		if(cnt==0) {
			heartRepository.insertHeart(h);
			cnt=1;
		}else {
			heartRepository.deleteHeart(h);
			cnt=0;
		}

		return cnt;
		
		
	}
	
	@PostMapping("/heart/delete")
	@ResponseBody
	public Integer heartDelete(@RequestParam String postCode, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		MemberVO tempVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);
		
		
		
		Heart h = new Heart();
		
		h.setHMemCode(Integer.toString(tempVO.getMemberCode()));
		h.setHPostCode(postCode);
		
		heartRepository.deleteHeart(h);
		int deleteCnt = heartRepository.countHeart(h);
		
		System.out.println("deleteCnt"+ deleteCnt);
		
		heartRepository.insertHeart(h);
		return deleteCnt;
		
	}
}
