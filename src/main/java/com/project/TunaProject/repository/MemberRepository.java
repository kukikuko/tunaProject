package com.project.TunaProject.repository;

import com.project.TunaProject.domain.MemberVO;

public interface MemberRepository {

	public MemberVO memberInsert(MemberVO memberVO);
	
	public MemberVO selectByEmail(String email);
}
