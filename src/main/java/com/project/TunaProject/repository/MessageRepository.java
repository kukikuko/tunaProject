package com.project.TunaProject.repository;

import java.util.List;

import com.project.TunaProject.domain.Message;
import com.project.TunaProject.domain.NewMessageInfo;

public interface MessageRepository {

	public List<Message> select_Message(int chat_code);
	
	public List<Message> select_Message_cur(int chat_code,int message_code);
	
	public void insert_Message(Message message);
	
	public int find_Message_Caller(int message_code);
	
	public NewMessageInfo find_Message_New(int chat_code,int message_code,int member_code);
	
	public Message find_message(int message_code);
}
