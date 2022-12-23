package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.TunaProject.domain.Category;
import com.project.TunaProject.domain.Post;

@Mapper
public interface CategoryItemMapper {

	public List<Category> selectAll();
	
}