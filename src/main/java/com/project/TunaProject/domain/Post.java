package com.project.TunaProject.eg.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data

public class Post {
	private String postCode;
	private int pMemCode;
	private String pTitle;
	private String pContent;
	private String pCreateTime;
	private String pCorrectionTime;
	private String pPrice;
	private boolean pSalesStatus;
	private boolean pOpenStatus;
	
	public Post() {}
}
