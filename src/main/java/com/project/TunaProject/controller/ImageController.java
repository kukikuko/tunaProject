package com.project.TunaProject.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.project.TunaProject.domain.Post;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImageController {
	
	@ResponseBody	//response 가 view 파일이 아니라 그냥 완료됐다고 메시지만 보내기 위해서  (REsponseBody에 Text로 리턴)
	@RequestMapping("/uploadFile")   //uploadFile 요청을 받아서 처리한 url 맵핑
	public String uploadFile(HttpServletRequest req ,MultipartRequest multiPartReq) {
		//multipartRequest -> multipart 로 요청이 들어왔을시에 기본 폼 데이터 + 파일 정보까지 동시에 받아서 처리해주는 변수
		
		log.info("uploadFile {}", req);
		log.info("mutilpart {}", multiPartReq);
		MultipartFile mpFile = multiPartReq.getFile("file");  //요청 넘어온 것중에 파일 받아내기
		log.info("file {}", mpFile);
//		log.info("Post {}", model);
		
		try {
			//파일 받은거 실제 경로에 옮겨서 저장처리하기
			Files.copy(mpFile.getInputStream(), Paths.get("D://uploadImg/"+mpFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			
			
			
			//이후에 필요에 따라서 DB에 저장하는 과정 실행 필요
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "ok";		
	}
}
