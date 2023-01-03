 package com.project.TunaProject.config;

import com.project.TunaProject.repository.*;
import com.project.TunaProject.repository.mybatis.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppBeanConfig {

	private final MemberMapper memberMapper;
	private final PostItemMapper postItemMapper;
	private final CategoryItemMapper categoryItemMapper;
	private final ImageItemMapper imageItemMapper;
	private final AdminMapper adminMapper;
	private final NotifyMapper notifyMapper;
	private final HeartMapper heartMapper;

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
	@Bean
	public ImageRepository imageRepository() {
		return new MybatisImageRepository(imageItemMapper);
	}

	@Bean
	public AdminRepository adminRepository() {
		return new MybatisAdminRepository(adminMapper);
	}
	
	@Bean
	public NotifyRepository notifyRepository() {
		return new MybatisNotifyRepository(notifyMapper);
	}
	@Bean
	public HeartRepository heartRepository() {
		return new MybatisHeartRepository(heartMapper);
	}
}
