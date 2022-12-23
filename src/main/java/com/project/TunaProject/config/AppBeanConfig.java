 package com.project.TunaProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.TunaProject.repository.CategoryRepository;
import com.project.TunaProject.repository.MemberRepository;
import com.project.TunaProject.repository.PostRepository;
import com.project.TunaProject.repository.mybatis.CategoryItemMapper;
import com.project.TunaProject.repository.mybatis.MemberMapper;
import com.project.TunaProject.repository.mybatis.MybatisCategoryRepository;
import com.project.TunaProject.repository.mybatis.MybatisMemberRepository;
import com.project.TunaProject.repository.mybatis.MybatisPostRepository;
import com.project.TunaProject.repository.mybatis.PostItemMapper;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppBeanConfig {

	private final MemberMapper memberMapper;
	private final PostItemMapper postItemMapper;
	private final CategoryItemMapper categoryItemMapper;

	@Bean
	public MemberRepository memberRepository() {
		return new MybatisMemberRepository(memberMapper);
	}
	@Bean
	public PostRepository postRepository() {
		return new MybatisPostRepository(postItemMapper);
	}
	@Bean
	public CategoryRepository categoryRepository() {
		return new MybatisCategoryRepository(categoryItemMapper);
	}
}
