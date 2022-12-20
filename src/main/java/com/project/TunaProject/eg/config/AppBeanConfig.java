package com.project.TunaProject.eg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.TunaProject.eg.repository.PostRepository;
import com.project.TunaProject.eg.repository.mybatis.MybatisPostRepository;
import com.project.TunaProject.eg.repository.mybatis.PostItemMapper;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppBeanConfig {
	private final PostItemMapper postItemMapper ;
	
	@Bean
	public PostRepository postRepository() {
		return new MybatisPostRepository(postItemMapper);
	}
}
