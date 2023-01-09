package com.project.TunaProject.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.project.TunaProject.domain.Chat;
import com.project.TunaProject.domain.Image;
import com.project.TunaProject.domain.Message;
import com.project.TunaProject.domain.Notify;
import com.project.TunaProject.domain.Post;
import com.project.TunaProject.img.FileStore;
import com.project.TunaProject.img.UploadFile;
import com.project.TunaProject.repository.ChatRepository;
import com.project.TunaProject.repository.ImageRepository;
import com.project.TunaProject.repository.MemberRepository;
import com.project.TunaProject.repository.MessageRepository;
import com.project.TunaProject.repository.PostRepository;
import com.project.TunaProject.repository.mybatis.MessageMapper;
import com.project.TunaProject.repository.mybatis.MybatisMessageRepository;
import com.project.TunaProject.repository.mybatis.MybatisNotifyRepository;

import jakarta.servlet.http.HttpServletRequest;
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
	private final ImageRepository imageRepository;
	private final MybatisNotifyRepository mybatisNotifyRepository;
	private final FileStore fileStore;
	
    @RequestMapping("/uuid_info/{uuid}")
    public String get_id(@PathVariable("uuid") String uuid)
    {
    	String member_code = memberRepository.selectByUUID(uuid).getMemberCode()+"";
    	return member_code;
    }
    @RequestMapping("/chat_title/find/{chat_code}")
    public String find_title(@PathVariable("chat_code") String chat_code)
    {
    	log.info("start");

    	String postcode =  chatRepository.findPostCode(Integer.parseInt(chat_code));
    	log.info("check_point 1");
    	 Post post = postRepository.selectByPostCode(postcode);
    	log.info("check_point 2");
 
    	return post.getPTitle()+"("+memberRepository.selectByCode(post.getPMemCode()).getMemberNick()+")";
    }
    
    @ResponseBody
    @RequestMapping("image/up")
    public String up_image(HttpServletRequest req,MultipartRequest multiPartReq)
    {
    	String img_code= " ";
		MultipartFile mpFile = multiPartReq.getFile("chatImage");  //요청 넘어온 것중에 파일 받아내기
		try {
			//파일 받은거 실제 경로에 옮겨서 저장처리하기
			
			UploadFile uf = fileStore.storeFile(mpFile);
			
			log.info("uf {}",uf);
			
			Image img = new Image();
			img.setImageFiles(uf.getStoreFileName());
			img.setImageName(uf.getUploadFileName());
			img.setITarget("2");
			img.setIType("0");

			img_code = imageRepository.insert(img).getImageCode()+"";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    	return img_code;
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
    		 Post post = postRepository.selectByPostCode(c.getPostCode());
    		 
    		chat_info_list += post.getPTitle()+"\0"+c.getChatCode()+"\0"+memberRepository.selectByCode(post.getPMemCode()).getMemberNick();
    	}
    	
    	if(!is_list)
    	{
    		//curChatCode member_code
    	}
    	
    	return chat_info_list;
    }
    

    
    @RequestMapping("/image/get/{img_code}")
    public String get_image(@PathVariable("img_code") String img_code)
    {
    	String str ="";
    	Image img = imageRepository.selectByImageCode(img_code);
    	if(img!=null)
    	{
        	str = img.getImageFiles();
    	}
    	return str;
    	
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
    	log.info(image_code);
    	log.info(message);
    	log.info(member_code);
    	log.info(chat_code);
    	log.info(px_size);

    	
        
    	Message m = new Message(message,Integer.parseInt(px_size),Integer.parseInt(member_code),Integer.parseInt(chat_code),Integer.parseInt(image_code));
    	messageRepository.insert_Message(m);
        
    }
    
    @PostMapping("message/notify")
    public void up_chat(HttpServletResponse resp,@RequestParam("MessageCode") String MessageCode,@RequestParam("ChatCode") String ChatCode,@RequestParam("doNotifyUser") String doNotifyUser) {
    	resp.setStatus(204);
    	
    	int NotifyUser = messageRepository.find_Message_Caller(Integer.parseInt(MessageCode));
    	Notify notify = new Notify();
    	notify.setDoNotifyMemberCode(doNotifyUser);
    	notify.setNotifyMemberCode(NotifyUser+"");
    	notify.setNotifyTarget(2);
    	notify.setNotifyType(MessageCode);
    	mybatisNotifyRepository.insertNotify(notify);
    	
    	//메세지창 상대 멤버 코드 찾기 
    	
    	}
    
	
}