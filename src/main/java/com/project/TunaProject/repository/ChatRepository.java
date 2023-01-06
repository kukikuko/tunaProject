package com.project.TunaProject.repository;

import java.util.List;

import com.project.TunaProject.domain.Chat;

public interface ChatRepository {
	public void insertChat(Chat chat);
	public String findChatCode(Chat chat);
	public List<Chat> selectMyChat(int memberCode);
	public List<Chat> selectMyChatByCur(int memberCode,int chatCode);
    public String findPostCode(int chatCode);

}
