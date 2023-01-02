package com.project.TunaProject.repository;

import com.project.TunaProject.domain.MemberVO;

public interface MemberRepository {

	public MemberVO memberInsert(MemberVO memberVO);
	
	public MemberVO selectByEmail(String email);
	
	public MemberVO selectByUUID(String activeUUID);
	
	public Integer idCheck(String email);
	
	public boolean updateMemberByEmail(MemberVO memberVo);
	
	//1225
	public boolean updatePassword(MemberVO memberVO);
	
	public void deleteMember(MemberVO memberVO);
	
	public boolean updateUUID(MemberVO memberVO);
}
