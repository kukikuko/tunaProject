package com.project.TunaProject.repository.mybatis;

import org.springframework.stereotype.Repository;

import com.project.TunaProject.domain.Notify;
import com.project.TunaProject.repository.NotifyRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MybatisNotifyRepository implements NotifyRepository{

	private final NotifyMapper notifyMapper; 
	
	@Override
	public Notify insertNotify(Notify notify) {
		// TODO Auto-generated method stub
		notifyMapper.insertNotify(notify);
		return notify;
	}

	@Override
	public Integer notifyCheck(Notify notify) {
		// TODO Auto-generated method stub
		int result = notifyMapper.notifyCheck(notify);
		return result;
	}

	@Override
	public List<Notify> selectNotifyAll(String target) {
		List<Notify> notifyList = notifyMapper.selectNotifyAll(target);
		return notifyList;
	}

	@Override
	public Integer notifyfilter(int messageCode) {
		// TODO Auto-generated method stub
		int result = notifyMapper.notifyfilter(messageCode);
		return result;
	}
}
