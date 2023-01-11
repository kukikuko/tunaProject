package com.project.TunaProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.TunaProject.domain.Chat;
import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.domain.Post;
import com.project.TunaProject.repository.ChatRepository;
import com.project.TunaProject.repository.ImageRepository;
import com.project.TunaProject.repository.MemberRepository;
import com.project.TunaProject.repository.MessageRepository;
import com.project.TunaProject.repository.PostRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
	
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	private final ChatRepository chatRepository;

    @RequestMapping("/add")
    String chatAdd(HttpServletRequest req,@RequestParam("postCode") String postCode,RedirectAttributes rAttr)
    {
    	Chat chat = new Chat();
    	String uuid_v ="";
    	Cookie[] cookies = req.getCookies();
    	for(Cookie c:cookies )
    	{
    		if(c.getName().equals("JSESSIONID"))
    		{
    			uuid_v = c.getValue().toString();
    			break;
    		}
    	}
    	int buyer_code=  memberRepository.selectByUUID(uuid_v).getMemberCode();
    	int seller_code = postRepository.selectByPostCode(postCode).getPMemCode();
    	//String image_path =  imageRepository.selectAll(postCode).get(0).getImageFiles();
    	chat.setPostCode(postCode);
    	chat.setBuyer(buyer_code);
    	chat.setSeller(seller_code);

    	chatRepository.insertChat(chat);// 해당 채팅방이 없을 떄만 으로 제한 
    	String chat_code = chatRepository.findChatCode(chat) +"";
    	//쿠키에 방 코드 저장
    	//자기 멤버 코드 찾아오고 세션 아이디로 -> 세션아이디로 멤버 코드 반환 해주는 주소 만들어 주기 'http://localhost:8080/uuid_info/'+uuid
		//chat xml호출 인서트
		
		return "redirect:http://localhost:3000/chat/"+chat_code;
    }
}
