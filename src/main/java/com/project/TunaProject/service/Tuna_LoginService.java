package com.project.TunaProject.service;

import org.springframework.stereotype.Service;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.repository.MemberRepository;
import com.project.TunaProject.repository.mybatis.MybatisMemberRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Tuna_LoginService {
	
	private final MemberRepository memberRepository;
	
	public MemberVO login(String email, String password) {
		MemberVO memberVO = memberRepository.selectByEmail(email);
		
		if(memberVO != null) {
			if(memberVO.getPassword().equals(password)) {
				return memberVO;
			}
		}
		return null;
	}
	
	//1225
	public MemberVO passwordCheck(String email, String password) {
		
		MemberVO memberVO = memberRepository.selectByEmail(email);
		
		if(memberVO != null) {
			if(memberVO.getPassword().equals(password)) {
				return memberVO;
			}
		}
		return null;
	}
}