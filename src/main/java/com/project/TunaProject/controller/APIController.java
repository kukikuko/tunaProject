package com.project.TunaProject.controller;


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
import com.project.TunaProject.domain.Message;
import com.project.TunaProject.repository.ChatRepository;
import com.project.TunaProject.repository.MemberRepository;
import com.project.TunaProject.repository.MessageRepository;
import com.project.TunaProject.repository.PostRepository;
import com.project.TunaProject.repository.mybatis.MessageMapper;
import com.project.TunaProject.repository.mybatis.MybatisMessageRepository;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class APIController {
	private final MessageRepository messageRepository;
	private final MemberRepository memberRepository;
	private final ChatRepository chatRepository;
	private final PostRepository postRepository;

    @RequestMapping("/uuid_info/{uuid}")
    public String get_id(@PathVariable("uuid") String uuid)
    {
    	String member_code = memberRepository.selectByUUID(uuid).getMemberCode()+"";
    	return member_code;
    }
    
    @RequestMapping("/chat/find/{member_code}/{curChatCode}")
    public String find_chat(@PathVariable("member_code") String member_code,@PathVariable("curChatCode") String curChatCode)
    {
    	String chat_info_list ="";
    	List<Chat> chat_list=null;
    	
    	if(Integer.parseInt(curChatCode)==0)
    	{
        	 chat_list = chatRepository.selectMyChat(Integer.parseInt(member_code));
    	}
    	else
    	{
        	chat_list = chatRepository.selectMyChatByCur(Integer.parseInt(member_code),Integer.parseInt(curChatCode));
    	}
    	
    	boolean is_list =false;
    	
    	
    	
    	for(Chat c:chat_list)
    	{
    		if(is_list==true)
    		{
    			chat_info_list +="\0";
    		}
    		else
    		{
        		is_list=true;
    		}
    		chat_info_list += postRepository.selectByPostCode(c.getPostCode()).getPTitle()+"\0"+c.getChatCode();
    	}
    	return chat_info_list;
    }
    
    
	
    @RequestMapping("/message/get/{chat_code}/{message_code}")
    public String get_chat(@PathVariable("chat_code") String chat_code,@PathVariable("message_code") String message_code) {
    	
    	String str ="";

    	List<Message> message_list =null;
        if(Integer.parseInt(message_code)==0)
        {
        	message_list =messageRepository.select_Message(Integer.parseInt(chat_code));
        }
        else
        {
        	message_list =messageRepository.select_Message_cur(Integer.parseInt(chat_code),Integer.parseInt(message_code));
        	
        }
        

    	
        
        boolean is_start =true;
    	for(Message m:message_list)
    	{
    		if(!is_start)
    		{
        		str+="\0";
    		}
    		else
    		{
        		is_start = false;
    		}
    		str+=m.all_data();
    	}
    	
    	return str;
      
    	 
    }
    @PostMapping("/message/up")
    public void up_chat(HttpServletResponse resp,@RequestParam("message") String message,@RequestParam("member_code") String member_code,@RequestParam("chat_code") String chat_code,@RequestParam("px_size") String px_size,@RequestParam("image_code") String image_code) {
    	resp.setStatus(204);
    	
    	
        
    	Message m = new Message(message,Integer.parseInt(px_size),Integer.parseInt(member_code),Integer.parseInt(chat_code),Integer.parseInt(image_code));
    	messageRepository.insert_Message(m);
        
    }
    
    @PostMapping("message/notify")
    public void up_chat(HttpServletResponse resp,@RequestParam("MessageCode") String MessageCode,@RequestParam("ChatCode") String ChatCode,@RequestParam("doNotifyUser") String doNotifyUser) {
    	resp.setStatus(204);

    	
    	
    	}
    
	
}