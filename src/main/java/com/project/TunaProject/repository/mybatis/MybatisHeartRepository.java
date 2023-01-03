package com.project.TunaProject.repository.mybatis;

import org.springframework.stereotype.Repository;

import com.project.TunaProject.domain.Heart;
import com.project.TunaProject.repository.HeartRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisHeartRepository implements HeartRepository{

	private final HeartMapper heartMapper;

	@Override
	public void insertHeart(Heart heart) {
		// TODO Auto-generated method stub
		heartMapper.insertHeart(heart);
	}

	@Override
	public Integer countHeart(Heart heart) {
		// TODO Auto-generated method stub
		int cnt = heartMapper.countHeart(heart);
		return cnt;
	}

	@Override
	public void deleteHeart(Heart heart) {
		// TODO Auto-generated method stub
		heartMapper.deleteHeart(heart);
	}

}
