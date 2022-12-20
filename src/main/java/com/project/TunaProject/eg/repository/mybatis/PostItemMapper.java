package com.project.TunaProject.eg.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.TunaProject.eg.domain.Post;

@Mapper
public interface PostItemMapper {
	public Integer insert(Post post);
	
	public Post selectByPostCode(String postCode);
	
	public List<Post> selectAll();
	
	public void update(@Param("postCode")String postCode, @Param("updatePost")Post post);
	
	public void deleteByPostCode(String postCode);
}