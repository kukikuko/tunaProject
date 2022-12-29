package com.project.TunaProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.TunaProject.repository.ChatRepository;
import com.project.TunaProject.repository.mybatis.ChatMapper;
import com.project.TunaProject.repository.mybatis.MybatisChatRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppBeanConfig {
	private final ChatMapper chatMapper;

    @Bean
    public ChatRepository chatRepository() {
        return new MybatisChatRepository(chatMapper);
//        return new ListMemberRepository();
    }
}
