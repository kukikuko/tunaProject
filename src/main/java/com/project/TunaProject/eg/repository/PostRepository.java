package com.project.TunaProject.eg.repository;

import java.util.List;



import com.project.TunaProject.eg.domain.Post;


public interface PostRepository {

	public Post insert(Post post);
	
	public Post selectByPostCode(String postCode);
	
	public List<Post> selectAll();
	
	public boolean update(String postCdoe, Post post);
	
	public void deleteByPostCode(String postCode);
}
