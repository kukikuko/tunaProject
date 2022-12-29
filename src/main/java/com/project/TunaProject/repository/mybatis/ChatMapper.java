package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.TunaProject.domain.Chat;

@Mapper
public interface ChatMapper {
	
	public List<Chat> select_chat(int chat_code);
	public List<Chat> select_chat_cur(@Param("chat_code")int chat_code, @Param("message_code") int message_code);
	public void insert_chat(Chat chat);

}
