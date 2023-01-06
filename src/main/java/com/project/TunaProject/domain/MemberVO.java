package com.project.TunaProject.domain;



import java.util.Date;

import lombok.Data;

import java.util.Date;

@Data
public class MemberVO {
	private int memberCode;		
	private String memberMail1;	//이메일 앞
	private String memberMail2;	//이메일 뒤
	private String memberMail;	//이메일 완성
	private String password;	//비밀번호
	private String memberName; 	//이름
	private int memberBirth;	//생년월일
	private String memberNick;	//닉네임
	private int memberPN1;		//전화번호 앞
	private int memberPN2;		//전화번호 중간
	private int memberPN3;		//전화번호 뒤
	private String memberPN;	//전체 전화번호
	private String memberAddr1;	//우편번호
	private String memberAddr2;	//도로명 or 지번
	private String memberAddr3;	//상세주소
	private String memberAddr4;	//주소 참고항목 null 가능
	private String adminCk;		//활동상태
	private Date regDate;		//가입일자
	private String activeUUID; //활성UUID (비활성시 NULL)
}

