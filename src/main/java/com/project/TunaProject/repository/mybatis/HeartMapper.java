package com.project.TunaProject.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.project.TunaProject.domain.Heart;

@Mapper
public interface HeartMapper {

	public void insertHeart(Heart heart);
	
	public Integer countHeart(Heart heart);
	
	public void deleteHeart(Heart heart);
	}
