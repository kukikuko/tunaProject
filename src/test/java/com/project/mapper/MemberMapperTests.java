//package com.project.mapper;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.project.TunaProject.domain.MemberVO;
//import com.project.TunaProject.mapper.MemberMapper;
//
//import lombok.RequiredArgsConstructor;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@RequiredArgsConstructor
//public class MemberMapperTests {
//
//	@Autowired
//	private MemberMapper membermapper;			//MemberMapper.java 인터페이스 의존성 주입
//	
//	//회원가입 쿼리 테스트 메서드
//	@Test
//	public void memberJoin() throws Exception{
//		MemberVO member = new MemberVO();
//		
////		member.setMemberCode(2);			//회원 id
//		member.setPassword("test");			//회원 비밀번호
//		member.setMemberName("사용자");		//회원 이름
//		member.setMemberMail("test");		//회원 메일
//		member.setMemberAddr1("test1");		//회원 우편번호
//		member.setMemberAddr2("test2");		//회원 주소
//		member.setMemberAddr3("test3");		//회원 상세주소
////		member.setAdminCk(1);
////		member.setRegDate(20221214);
//		
//		membermapper.memberJoin(member);			//쿼리 메서드 실행
//		
//	}
//	
//	
//	
//}