 	package com.project.TunaProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.TunaProject.repository.MemberRepository;
import com.project.TunaProject.repository.mybatis.MemberMapper;
import com.project.TunaProject.repository.mybatis.MybatisMemberRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppBeanConfig {
	
	private final MemberMapper memberMapper;
	
	@Bean
	public MemberRepository memberRepository() {
		return new MybatisMemberRepository(memberMapper);
	}
}
