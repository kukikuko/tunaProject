package com.project.TunaProject.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Post {
	private String postCode;
	private String postCtCode;
	private int pMemCode;
	private String memNick;
	private String pTitle;
	private String pContent;
	private Date pCreateTime;
	private Date pCorrectionTime;
	private String pPrice;
	private SaleType pSalesStatus;
	private String pOpenStatus;
	private int pView;
	private String ctName;
	
//	public Post() {}
}
