package com.project.TunaProject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.TunaProject.repository.MessageRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
	
    @RequestMapping("/add")
    String chatAdd(HttpServletResponse resp,@RequestParam("postCode") String postCode,RedirectAttributes rAttr)
    {
    	//게시자 찾아오고 postcode로 -> xml 추가
    	//자기 멤버 코드 찾아오고 세션 아이디로 -> 세션아이디로 멤버 코드 반환 해주는 주소 만들어 주기 'http://localhost:8080/uuid_info/'+uuid
    	
    	
		Cookie cookie = new Cookie("postCode",postCode);
		cookie.setDomain("localhost");
		cookie.setPath("/");
		// 30초간 저장
		cookie.setMaxAge(30*60);
		cookie.setSecure(false);
		resp.addCookie(cookie);
		
		//chat xml호출 인서트
		
		
		return "redirect:http://localhost:3000/";
    }


}
