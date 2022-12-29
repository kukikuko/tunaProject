package com.project.TunaProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/testid")
public class Testcont {
	@RequestMapping("/create")
	public String createCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie("member_code","2");
		cookie.setDomain("localhost");
		cookie.setPath("/");
		// 30초간 저장
		cookie.setMaxAge(30*60);
		cookie.setSecure(false);
		response.addCookie(cookie);
		
		Cookie cookie2 = new Cookie("chat_code","1");
		cookie2.setDomain("localhost");
		cookie2.setPath("/");
		// 30초간 저장
		cookie2.setMaxAge(30*60);
		cookie2.setSecure(false);
		response.addCookie(cookie2);
		
		return "redirect:/testid/createCookie";
	}
}
