package com.project.TunaProject.repository;

import java.util.List;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.domain.Post;

public interface MemberRepository {

	//회원가입
	public MemberVO memberInsert(MemberVO memberVO);
	
	public MemberVO selectByEmail(String email);

	public MemberVO selectByUUID(String activeUUID);
	
	//id중복체크
	public Integer idCheck(String email);
	
	public boolean updateMemberByEmail(MemberVO memberVo);
	
	//1225
	public boolean updatePassword(MemberVO memberVO);
	
	public void deleteMember(MemberVO memberVO);
	
	public boolean updateUUID(MemberVO memberVO);

	//회원탈퇴
	public boolean updateAdminCk(MemberVO memberVO);
	
	//회원 탈퇴시, 게시물 공개여부를 변경하기
	public boolean updatePopenStatus(MemberVO memberVO);
	public MemberVO selectByCode(int code);
	//비밀번호 찾기
	public String emailFindPw(String email);
	
	//자신이 올린 게시물
	public List<Post> selectByMemberCode(Integer memberCode);
	
	//자신이 찜한 게시물을 내 활동에서 보여주는
	public List<Post> selectByMemberAndHeart(Integer memberCode);
}
