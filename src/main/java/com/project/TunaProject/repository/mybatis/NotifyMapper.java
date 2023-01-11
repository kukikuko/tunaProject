package com.project.TunaProject.repository.mybatis;

import com.project.TunaProject.domain.ChatMSG;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.TunaProject.domain.Image;
import com.project.TunaProject.domain.Notify;

import java.util.List;

@Mapper
public interface NotifyMapper {

	public Integer insertNotify(Notify notify);
	
	public Integer notifyCheck(Notify notify);

	public List<Notify> selectNotifyAll(String target);
	
	public Integer notifyfilter(int messageCode);

	public List<ChatMSG> selectNotifyChatAll();

}
