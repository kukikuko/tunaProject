package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.TunaProject.domain.Chat;
import com.project.TunaProject.repository.ChatRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisChatRepository implements ChatRepository {

	private final ChatMapper chatMapper;
	
	@Override
	public List<Chat> select_chat(int chat_code) {
		// TODO Auto-generated method stub
		List<Chat> c = chatMapper.select_chat(chat_code);
		return c;
	}

	@Override
	public List<Chat> select_chat_cur(int chat_code, int message_code) {
		// TODO Auto-generated method stub
		List<Chat> c = chatMapper.select_chat_cur(chat_code,message_code);

		return c;
	}

	@Override
	public void insert_chat(Chat chat) {
		
		chatMapper.insert_chat(chat);
		// TODO Auto-generated method stub
		
		
	}

}
