package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.TunaProject.domain.Cexit;
import com.project.TunaProject.domain.Chat;
import com.project.TunaProject.domain.CurView;
import com.project.TunaProject.repository.ChatRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	@Override
	public String findPostCode(int chatCode) {
		return chatMapper.findPostCode(chatCode);
		// TODO Auto-generated method stub
	}

	@Override
	public void updateCurview(CurView cv) {
		// TODO Auto-generated method stub
		if(cv.getMessageCode()==0)
		{
			return;
		}
		 chatMapper.updateCurview1(cv);
		 chatMapper.updateCurview2(cv);
	}

	@Override
	public Chat findChatInfo(int chatCode) {
		return chatMapper.findChatInfo(chatCode);
	}

	@Override
	@Transactional
	public void exitChat(int memberCode, int chatCode) {

		Cexit cexit  = new Cexit();
//		
		
		cexit.setChatCode(chatCode);
		cexit.setMemberCode(memberCode);
		
		chatMapper.exitChatBuyer(cexit);
		chatMapper.exitChatSeller(cexit);

		
	}

}
