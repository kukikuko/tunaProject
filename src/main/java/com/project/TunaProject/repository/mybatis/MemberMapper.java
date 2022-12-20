package com.project.TunaProject.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.project.TunaProject.domain.MemberVO;


@Mapper
public interface MemberMapper {

	//회원가입하는 메소드
	public Integer memberInsert(MemberVO memberVO);
	
	//로그인 하는 메소드
	public MemberVO selectByEmail(String email);
}
