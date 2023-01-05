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
	public void insertChat(Chat chat) {
		// TODO Auto-generated method stub
		chatMapper.insertChat(chat);
	}

	@Override
	public String findChatCode(Chat chat) {
		// TODO Auto-generated method stub
	
		
		Chat c = chatMapper.findChatCode(chat);
		if(c==null)
		{
			return "main";
		}
		return c.getChatCode()+"";
	}

	@Override
	public List<Chat> selectMyChat(int memberCode) {
		// TODO Auto-generated method stub
		 List<Chat> chat_list = chatMapper.selectMyChat(memberCode);

		return chat_list;
	}

	@Override
	public List<Chat> selectMyChatByCur(int memberCode, int chatCode) {
		// TODO Auto-generated method stub
		 List<Chat> chat_list = chatMapper.selectMyChatByCur(memberCode,chatCode);

		return chat_list;
	}

}
