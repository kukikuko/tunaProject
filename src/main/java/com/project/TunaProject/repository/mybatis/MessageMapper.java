package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.TunaProject.domain.Message;
import com.project.TunaProject.domain.NewMessageInfo;

@Mapper
public interface MessageMapper {
	
	public List<Message> select_Message(int chat_code);
	public List<Message> select_Message_cur(@Param("chat_code")int chat_code, @Param("message_code") int message_code);
	public void insert_Message(Message message);
	public Integer find_Message_Caller(int message_code);
	public Integer new_Message_Content(int chat_code);
	public Integer new_Message_Count(@Param("chat_code")int chat_code, @Param("message_code") int message_code,@Param("member_code") int member_code);

	public Message find_message(int message_code);


}
