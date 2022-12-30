package com.project.TunaProject.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Post {
	private String postCode;
	private String postCtCode;
	private int pMemCode;
	private String pTitle;
	private String pContent;
	private String pCreateTime;
	private String pCorrectionTime;
	private String pPrice;
	private String pSalesStatus;
	private String pOpenStatus;
	private int pView;
	private String ctName;
	
//	public Post() {}
}
