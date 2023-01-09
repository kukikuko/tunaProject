package com.project.TunaProject.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PostForCategory {
	private String postCode;
	private String postCtCode;
	private int pMemCode;
	private String memNick;
	private String heart;
	private String ctName;
	private String pTitle;
	private String pContent;
	private Date pCreateTime;
	private Date pCorrectionTime;
	private String pPrice;
	private SaleType pSalesStatus;
	private String pOpenStatus;
	private int pView;

}