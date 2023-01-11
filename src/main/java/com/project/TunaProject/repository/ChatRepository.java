package com.project.TunaProject.repository;

import java.util.List;

import com.project.TunaProject.domain.Chat;
import com.project.TunaProject.domain.CurView;

public interface ChatRepository {
	
	public void insertChat(Chat chat);
	
	public String findChatCode(Chat chat);
	
	public List<Chat> selectMyChat(int memberCode);
	
	public List<Chat> selectMyChatByCur(int memberCode,int chatCode);
    
	public String findPostCode(int chatCode);
    
	public void updateCurview(CurView cv);
    
	public Chat findChatInfo(int chatCode);

}
