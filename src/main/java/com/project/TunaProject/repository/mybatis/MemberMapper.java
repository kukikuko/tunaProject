package com.project.TunaProject.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.project.TunaProject.domain.MemberVO;


@Mapper
public interface MemberMapper {

	//회원가입하는 메소드
	public Integer memberInsert(MemberVO memberVO);
	
	//로그인 하는 메소드
	public MemberVO selectByEmail(String email);
	
	public Integer idCheck(String email);
	
	public void updateMemberByEmail(MemberVO memberVO);
	
	//1225
	public void updatePassword(MemberVO memberVO);
	
	//회원탈퇴 메소드
	public void deleteMember(MemberVO memberVO);
	
	//비밀번호 찾는 메소드
	public String emailFindPw(String email);
}
