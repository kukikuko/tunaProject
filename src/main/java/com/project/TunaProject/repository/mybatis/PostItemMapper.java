package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.TunaProject.domain.Post;

@Mapper
public interface PostItemMapper {
//	public Integer insert(@Param("post") Post post, @Param("membercode") int membercode);
	public Integer insert(Post post);
	
	public Post selectByPostCode(String postCode);
	
	public List<Post> selectAll();
	
	public void update(@Param("postCode")String postCode, @Param("updatePost")Post post);
	
	public void updateDelete(@Param("postCode")String postCode);
	
	public void viewCont(String postCode);
	
}