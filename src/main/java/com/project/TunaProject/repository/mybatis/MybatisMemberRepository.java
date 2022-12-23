package com.project.TunaProject.repository.mybatis;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.domain.Post;
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

	@Override
	public Integer idCheck(String email) {
		// TODO Auto-generated method stub
		Integer cnt = memberMapper.idCheck(email);
		
		return cnt;
	}
	
	@Override
	@Transactional
	public boolean updateMemberByEmail(MemberVO memberVO) {
		// TODO Auto-generated method stub
		boolean result = false;
		memberMapper.updateMemberByEmail(memberVO);
		result = true;
		
		return result;
	}

}
