package com.project.TunaProject.repository;

import java.util.List;

import com.project.TunaProject.domain.Post;


public interface PostRepository {

	public Post insert(Post post, int memberCode, String ctCode);
	
	public Post selectByPostCode(String postCode);
	
	public List<Post> selectAll();
	
	public boolean update(String postCode, Post post);
	
	public boolean updateDelete(String postCode);
	
	public void viewCont(String postCode);
	
	public List<Post> selectSearch(String keyword);
}
