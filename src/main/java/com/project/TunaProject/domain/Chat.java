package com.project.TunaProject.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Chat {
	private int chatCode;		//채팅코드
	private String postCode;	//글코드
	private int seller;			//판매자
	private int sellerCurView;	//판매자가 본 최근 메세지
	private int buyer;			//구매자
	private int buyerCurView;	//구매자가 본 최근 메세지
	
	
	
	
	
}
