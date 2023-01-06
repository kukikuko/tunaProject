package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.TunaProject.domain.Message;

@Mapper
public interface MessageMapper {
	
	public List<Message> select_Message(int chat_code);
	public List<Message> select_Message_cur(@Param("chat_code")int chat_code, @Param("message_code") int message_code);
	public void insert_Message(Message message);

}
