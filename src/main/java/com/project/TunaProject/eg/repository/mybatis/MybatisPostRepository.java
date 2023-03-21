package com.project.TunaProject.eg.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.TunaProject.eg.domain.Post;
import com.project.TunaProject.eg.repository.PostRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;




@Repository
@RequiredArgsConstructor
public class MybatisPostRepository implements PostRepository{

	private final PostItemMapper postItemMapper;

	@Override
	public Post insert(Post post) {
		// TODO Auto-generated method stub
		Integer result = postItemMapper.insert(post);
		return post;
	}

	@Override
	public Post selectByPostCode(String postCode) {
		// TODO Auto-generated method stub
		Post post = postItemMapper.selectByPostCode(postCode);
		return post;
	}

	@Override
	public List<Post> selectAll() {
		// TODO Auto-generated method stub
		List<Post> postList = postItemMapper.selectAll();
		return postList;
	}

	@Override
	@Transactional
	public boolean update(String postCdoe, Post post) {
		// TODO Auto-generated method stub
		boolean result = false;
		postItemMapper.update(postCdoe, post);
		result = true;
		
		return result;
	}

	@Override
	public void deleteByPostCode(String postCode) {
		// TODO Auto-generated method stub
		postItemMapper.deleteByPostCode(postCode);
	}
		
}
