package com.project.TunaProject.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.TunaProject.domain.Post;
import com.project.TunaProject.repository.PostRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;




@Repository
@RequiredArgsConstructor
public class MybatisPostRepository implements PostRepository{

	private final PostItemMapper postItemMapper;

	@Override
	public Post insert(Post post, int membercode, String ctCode) {
		// TODO Auto-generated method stub
//		Post postItem = postItemMapper.insert(post, membercode);
		post.setPMemCode(membercode);
		post.setPostCtCode(ctCode);
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
	public boolean update(String postCode, Post post) {
		// TODO Auto-generated method stub
		boolean result = false;
		postItemMapper.update(postCode, post);
		result = true;
		
		return result;
	}

	@Override
	public boolean updateDelete(String postCode) {
		// TODO Auto-generated method stub
		boolean result = false;
		postItemMapper.updateDelete(postCode);
		result = true;		
		return result;
	}

	@Override
	public void viewCont(String postCode) {
		// TODO Auto-generated method stub
		postItemMapper.viewCont(postCode);
	}

	@Override
	public List<Post> selectSearch(String keyword) {
		// TODO Auto-generated method stub
		List<Post> postList = postItemMapper.selectSearch(keyword);
		return postList;
	}
		
}
