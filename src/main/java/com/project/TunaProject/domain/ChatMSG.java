package com.project.TunaProject.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ChatMSG {

    private int messageCode;
    private int chatCode;
    private String contents;
    private int imageCode;
    private String notifyCode;			//신고코드
    private int notifyTarget;	//글 1, 채팅 2
    private String notifyType;		//글or채팅 코드
    private Date notifyTime;		//신고 접수된 시간
    private String doNotifyMemberCode;	//신고한 회원
    private String notifyMemberCode;	//신고당한 회원
    private String imageFiles;

}
