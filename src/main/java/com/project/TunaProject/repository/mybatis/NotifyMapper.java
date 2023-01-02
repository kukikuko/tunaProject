package com.project.TunaProject.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.project.TunaProject.domain.Notify;

@Mapper
public interface NotifyMapper {

	public Integer insertNotify(Notify notify);
}
