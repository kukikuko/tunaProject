package com.project.TunaProject.repository;

import java.util.List;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.domain.Post;

public interface MemberRepository {

	//회원가입
	public MemberVO memberInsert(MemberVO memberVO);
	
	public MemberVO selectByEmail(String email);
	
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
	
	//자신이 올린 게시물
	public List<Post> selectByMemberCode(Integer memberCode);
	
	//자신이 찜한 게시물을 내 활동에서 보여주는
	public List<Post> selectByMemberAndHeart(Integer memberCode);
}
