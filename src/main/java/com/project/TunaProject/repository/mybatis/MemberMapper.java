package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.domain.Post;


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
	
	//자신이 올린 게시물 확인
	public List<Post> selectByMemberCode(Integer memberCode);
	
	//자신이 찜한 게시물을 내 활동에서 보여주는
	public List<Post> selectByMemberAndHeart(Integer memberCode);
	
}
