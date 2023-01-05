package com.project.TunaProject.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.project.TunaProject.domain.Notify;

import java.util.List;

@Mapper
public interface NotifyMapper {

	public Integer insertNotify(Notify notify);
	
	public Integer notifyCheck(Notify notify);

	public List<Notify> selectNotifyAll(String target);
}
