package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.TunaProject.domain.Post;
import com.project.TunaProject.domain.PostCard;

@Mapper
public interface PostItemMapper {
//	public Integer insert(@Param("post") Post post, @Param("membercode") int membercode);
	public Integer insert(Post post);
	
	public Post selectByPostCode(String postCode);
	
	public List<Post> selectAll();
	
	public void update(@Param("postCode")String postCode, @Param("updatePost")Post post);
	
	public void updateDelete(@Param("postCode")String postCode);
	public void ACCOUNTUPDATE(@Param("postCode")String postCode,@Param("accountcode")String accountcode);
	
	public void viewCont(String postCode);
	
	public List<Post> selectSearch(@Param("keyword") String keyword);
	
	//조회수 top10
	public List<Post> orderByPview();
	
	public PostCard selectCard(String postCode); 
}