package com.project.TunaProject.Controller;


import java.time.LocalDate;
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
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
	private final ChatRepository chatRepository;

	
    @RequestMapping("/get/{chat_code}/{message_code}")
    public String get_chat(@PathVariable("chat_code") String chat_code,@PathVariable("message_code") String message_code) {
    	
    	String str ="";

    	List<Chat> chat_list =null;
        if(Integer.parseInt(message_code)==0)
        {
        
        
         chat_list =chatRepository.select_chat(Integer.parseInt(chat_code));
        }
        else
        {
             chat_list =chatRepository.select_chat_cur(Integer.parseInt(chat_code),Integer.parseInt(message_code));
        	
        }
        

    	
        
        boolean is_start =true;
    	for(Chat c:chat_list)
    	{
    		if(!is_start)
    		{
        		str+="\0";
    		}
    		else
    		{
        		is_start = false;
    		}
    		str+=c.all_data();
    	}
    	
    	return str;
      
    	 
    }
    @PostMapping("/up")
    public void up_chat(HttpServletResponse resp,@RequestParam("message") String message,@RequestParam("member_code") String member_code,@RequestParam("chat_code") String chat_code,@RequestParam("px_size") String px_size) {
    	resp.setStatus(204);
    	
        
        Chat c = new Chat(message,Integer.parseInt(px_size),Integer.parseInt(member_code),Integer.parseInt(chat_code));
        chatRepository.insert_chat(c);
        
    }
    
	
}