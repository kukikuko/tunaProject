package com.project.TunaProject.repository.mybatis;

import org.springframework.stereotype.Repository;

import com.project.TunaProject.domain.Chat;
import com.project.TunaProject.repository.ChatRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisChatRepository implements ChatRepository {

	private final ChatMapper chatMapper;

	@Override
	public void insertChat(Chat chat) {
		// TODO Auto-generated method stub
		chatMapper.insertChat(chat);
	}

}
