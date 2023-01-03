package com.project.TunaProject.domain;

import lombok.Data;

@Data
public class PostForCategory {
	
	private String postCode;
	private String postCtCode;
	private int pMemCode;
	private String memNick;
	private String ctName;
	private String pTitle;
	private String pContent;
	private String pCreateTime;
	private String pCorrectionTime;
	private String pPrice;
	private String pSalesStatus;
	private String pOpenStatus;
	private int pView;
	
	

}
