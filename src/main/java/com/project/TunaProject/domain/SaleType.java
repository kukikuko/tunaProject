package com.project.TunaProject.domain;

public enum SaleType {
	Y("판매중"), S("예약중"), N("판매완료");
	
	private final String memo;
	
	SaleType(String memo){
		this.memo = memo;
	}
	
	public String getMemo() {
		return memo;
	}
	
}
