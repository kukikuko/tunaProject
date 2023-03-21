package com.project.TunaProject.domain;

import lombok.Data;

@Data
public class PostCard {
	private String postCode;	//게시글 코드
	private String pTitle;		//게시물 제목
	private String pPrice;		//게시글 가격
	private int pView;			//게시글 조회수
	private String imageFiles;	//게시글에 등록된 이미지
}
