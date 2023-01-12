package com.project.TunaProject.repository;

import com.project.TunaProject.domain.ChatMSG;
import com.project.TunaProject.domain.Notify;

import java.util.List;

public interface NotifyRepository {

	public Notify insertNotify(Notify notify);
	
	public Integer notifyCheck(Notify notify);

	public List<Notify> selectNotifyAll(String target);

	public Integer notifyfilter(int messageCode);

	public List<ChatMSG> selectNotifyChatAll();

	public void deleteNotify(int notifyType);
}
