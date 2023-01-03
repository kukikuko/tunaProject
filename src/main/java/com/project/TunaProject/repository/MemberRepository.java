package com.project.TunaProject.repository;

import com.project.TunaProject.domain.MemberVO;

public interface MemberRepository {

	//회원가입
	public MemberVO memberInsert(MemberVO memberVO);
	
	public MemberVO selectByEmail(String email);

	public MemberVO selectByCode(int code);

	//id중복체크
	public Integer idCheck(String email);
	
	//회원정보 수정
	public boolean updateMemberByEmail(MemberVO memberVo);
	
	//1225
	public boolean updatePassword(MemberVO memberVO);
	
	//회원탈퇴
	public void deleteMember(MemberVO memberVO);
	
	//비밀번호 찾기
	public String emailFindPw(String email);
}
