package com.project.TunaProject.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Data
public class Message {
	private int messageCode;
	private int chatCode;
	private int memberCode;
	private String contents; 
	private LocalDateTime time;
	private int imageCode;
	private int pixelLength;
	
	public Message()
	{
		
	}
	
	public Message(String contents,int pixelLength,int memberCode,int chatCode)
	{
		this.contents=contents;
		this.pixelLength=pixelLength;
		this.memberCode = memberCode;
		this.chatCode =chatCode;
	}
	
	public String all_data()
	{
		
		return this.getMessageCode() +"\0"+this.getMemberCode()+"\0"+this.getContents()+"\0"+this.getTime()+"\0"+this.getImageCode()+"\0"+this.getPixelLength();
	}

}
