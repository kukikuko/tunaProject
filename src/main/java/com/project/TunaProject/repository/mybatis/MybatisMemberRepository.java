package com.project.TunaProject.repository.mybatis;

import java.util.List;

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
	
	//1225
	@Override
	@Transactional
	public boolean updatePassword(MemberVO memberVO) {
		// TODO Auto-generated method stub
		boolean result = false;
		memberMapper.updatePassword(memberVO);
		result = true;
		
		return result;
	}

	//회원탈퇴 메소드
	@Override
	public void deleteMember(MemberVO memberVO) {
		// TODO Auto-generated method stub
		memberMapper.deleteMember(memberVO);
	}
	
	//비밀번호 찾기
	@Override
	public String emailFindPw(String email) {
		// TODO Auto-generated method stub
		System.out.println("여기" + email);
		String password = memberMapper.emailFindPw(email);
		System.out.println("여기1" + password);
		
		return password;
	}

	@Override
	public List<Post> selectByMemberCode(Integer memberCode) {
		// TODO Auto-generated method stub
		List<Post> postList = memberMapper.selectByMemberCode(memberCode);
		
		return postList;
		
	}
	
	
	
	

}
