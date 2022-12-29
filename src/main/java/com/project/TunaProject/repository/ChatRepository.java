package com.project.TunaProject.repository;

import java.util.List;

import com.project.TunaProject.domain.Chat;

public interface ChatRepository {

	public List<Chat> select_chat(int chat_code);
	public List<Chat> select_chat_cur(int chat_code,int message_code);
	public void insert_chat(Chat chat);
	
}
