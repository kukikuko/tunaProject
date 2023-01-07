package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.TunaProject.domain.Chat;
@Mapper
public interface ChatMapper {

	public void insertChat(Chat chat);
	public Chat findChatCode(Chat chat);
	public List<Chat> selectMyChat(int memberCode);
	//인서트
	public List<Chat> selectMyChatByCur(@Param("memberCode")int memberCode,@Param("chatCode")int chatCode);
    public String findPostCode(int chatCode);
    
	
	
}
