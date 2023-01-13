package com.project.TunaProject.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public Integer notifyPost(@RequestParam Map<String, String> data, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		MemberVO tempVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);
		
		Notify n = new Notify();
		n.setNotifyType(data.get("postCode"));
		n.setNotifyMemberCode(data.get("pMemCode"));
		n.setDoNotifyMemberCode(String.valueOf(tempVO.getMemberCode()));
		n.setNotifyTarget(1);
		
		int cnt = mybatisNotifyRepository.notifyCheck(n);
		if(cnt==0) {
			mybatisNotifyRepository.insertNotify(n);
		}
		return cnt;
	}
}
