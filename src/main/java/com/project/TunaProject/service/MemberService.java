package com.project.TunaProject.service;

import com.project.TunaProject.domain.MemberVO;

public interface MemberService {
	
	//회원가입
	public void memberJoin(MemberVO member) throws Exception;
}
