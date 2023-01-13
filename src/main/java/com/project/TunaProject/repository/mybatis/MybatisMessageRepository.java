package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.TunaProject.controller.APIController;
import com.project.TunaProject.domain.Message;
import com.project.TunaProject.domain.NewMessageInfo;
import com.project.TunaProject.repository.MessageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisMessageRepository implements MessageRepository {

	private final MessageMapper messageMapper;
	
	@Override
	public List<Message> select_Message(int chat_code) {
		// TODO Auto-generated method stub
		List<Message> m = messageMapper.select_Message(chat_code);
		return m;
	}

	@Override
	public List<Message> select_Message_cur(int chat_code, int message_code) {
		// TODO Auto-generated method stub
		List<Message> m = messageMapper.select_Message_cur(chat_code,message_code);

		return m;
	}

	@Override
	public void insert_Message(Message message) {
		 messageMapper.insert_Message(message);
		// TODO Auto-generated method stub
	}

	@Override
	public int find_Message_Caller(int message_code) {
		// TODO Auto-generated method stub
		return messageMapper.find_Message_Caller(message_code).intValue();
		
	}

	@Override
	public NewMessageInfo find_Message_New(int chat_code, int message_code,int member_code) {
		// TODO Auto-generated method stub
		NewMessageInfo nmi = new NewMessageInfo();
	//만약 아무메세지도 없으면 0
		nmi.setLastCode(messageMapper.new_Message_Content(chat_code).intValue());
		nmi.setCountMessage(messageMapper.new_Message_Count(chat_code,message_code,member_code).intValue());
		return nmi;
	}

	@Override
	public Message find_message(int message_code) {
		// TODO Auto-generated method stub
		return messageMapper.find_message(message_code);
	}
}
