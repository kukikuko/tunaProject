package com.project.TunaProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.TunaProject.service.EmailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@org.springframework.stereotype.Controller
public class EmailController {
	
	private final EmailService emailService;
	
	@GetMapping("/emailConfirm")
	public String getemail() {

		  return "confirm";
		}

	@PostMapping("/emailConfirm")
	@ResponseBody
	public String emailConfirm(@RequestParam String email) throws Exception {
		
//	  String email = memberMail1 + "@" + memberMail2;
	  String confirm = emailService.sendSimpleMessage(email);
	
	  return confirm;
	}
	}