package com.project.TunaProject.repository;

import com.project.TunaProject.domain.MemberVO;

public interface MemberRepository {

	public MemberVO memberInsert(MemberVO memberVO);
	
	public MemberVO selectByEmail(String email);
	
	public Integer idCheck(String email);
	
	public boolean updateMemberByEmail(MemberVO memberVo);
	
	//1225
	public boolean updatePassword(MemberVO memberVO);
}
