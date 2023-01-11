package com.project.TunaProject.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PostForCategory {
	private String postCode;		//글코드
	private String postCtCode;		//글 카테고리 코드
	private int pMemCode;			//회원코드
	private String memNick;			//회원 닉네임
	private String heart;			//찜
	private String ctName;			//
	private String pTitle;			//글 제목
	private String pContent;		//글 내용
	private Date pCreateTime;		//글 작성시간
	private Date pCorrectionTime;	//글 수정시간
	private String pPrice;			//가격
	private SaleType pSalesStatus;	//판매상태(Y, S ,N)
	private String pOpenStatus;		//공개여부(Y, S ,N)
	private int pView;				//조회수

}