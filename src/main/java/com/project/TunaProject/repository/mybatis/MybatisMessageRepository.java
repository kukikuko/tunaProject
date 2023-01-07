package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.TunaProject.domain.Message;
import com.project.TunaProject.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

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
		return messageMapper.find_Message_Caller(message_code).getMemberCode();
		
	}

}
