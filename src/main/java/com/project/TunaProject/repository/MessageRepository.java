package com.project.TunaProject.repository;

import java.util.List;

import com.project.TunaProject.domain.Message;

public interface MessageRepository {

	public List<Message> select_Message(int chat_code);
	public List<Message> select_Message_cur(int chat_code,int message_code);
	public void insert_Message(Message message);
	
}
