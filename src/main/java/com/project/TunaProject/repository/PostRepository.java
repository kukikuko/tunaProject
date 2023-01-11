package com.project.TunaProject.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.TunaProject.domain.Post;
import com.project.TunaProject.domain.PostCard;


public interface PostRepository {

	public Post insert(Post post, int memberCode, String ctCode);
	
	public Post selectByPostCode(String postCode);
	
	public List<Post> selectAll();
	
	public boolean update(String postCode, Post post,  String ctCode);
	
	public boolean updateDelete(String postCode);
	
	public void viewCont(String postCode);
	
	public List<Post> selectSearch(String keyword);
	
	public boolean ACCOUNTUPDATE(@Param("postCode")String postCode,@Param("accountcode")String accountcode);

	//조회수 top10
	public List<Post> orderByPview();
	
	public PostCard selectCard(String postCode); 
	
}
