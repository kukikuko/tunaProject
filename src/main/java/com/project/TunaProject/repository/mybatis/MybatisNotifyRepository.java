package com.project.TunaProject.repository.mybatis;

import org.springframework.stereotype.Repository;

import com.project.TunaProject.domain.Notify;
import com.project.TunaProject.repository.NotifyRepository;

import lombok.RequiredArgsConstructor;

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

}
