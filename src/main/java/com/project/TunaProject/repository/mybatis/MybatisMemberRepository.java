package com.project.TunaProject.repository.mybatis;

import org.springframework.stereotype.Repository;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisMemberRepository implements MemberRepository{

	private final MemberMapper memberMapper;
	
	
	@Override
	public MemberVO memberInsert(MemberVO memberVO) {
		// TODO Auto-generated method stub
		memberMapper.memberInsert(memberVO);
		
		return memberVO;
	}
	
	@Override
	public MemberVO selectByEmail(String email) {
		// TODO Auto-generated method stub
		
		MemberVO memberVO = memberMapper.selectByEmail(email);
		
		return memberVO;
	
	}

}
