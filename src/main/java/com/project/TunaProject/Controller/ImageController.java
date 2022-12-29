package com.project.TunaProject.Controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.TunaProject.domain.Chat;
import com.project.TunaProject.repository.ChatRepository;
import com.project.TunaProject.repository.mybatis.ChatMapper;
import com.project.TunaProject.repository.mybatis.MybatisChatRepository;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

	
   
    @PostMapping("/up")
    public void up_chat(HttpServletResponse resp,@RequestParam("img") String img) {
    	resp.setStatus(204);
    	
    	//여기서 메세지 코드 지정
    	log.info(img);
    }
    
	
}