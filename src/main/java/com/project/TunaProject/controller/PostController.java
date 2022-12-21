package com.project.TunaProject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.TunaProject.domain.Post;
import com.project.TunaProject.repository.PostRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

	private final PostRepository postRepository;
	
	
	@GetMapping
	public String posts(Model model, HttpServletRequest rep) {
		List<Post> postList = postRepository.selectAll();
		model.addAttribute("posts", postList);
		return "/posts/posts";
	}
	@GetMapping("/{postCode}")
	public String post(Model model, @PathVariable("postCode") String postCode) {
		log.info(postCode);
		Post postItem = postRepository.selectByPostCode(postCode);
		log.info("postItem {}", postItem);
		model.addAttribute("post",postItem);
		
		return "/posts/post";
		
	}
	
	@GetMapping("/writing")
	public String postWriting(Model model) {
		model.addAttribute("post", new Post());
		return "/posts/writing";
	}
	
	@PostMapping("/writing")
	public String postWritingInsert(@ModelAttribute Post postItem, RedirectAttributes rAttr) {
		
		postRepository.insert(postItem);
		log.info("postItem {}", postItem);
		rAttr.addAttribute("postCode",postItem.getPostCode());
		
		return "redirect:/posts/{postCode}";
	}
	
	@GetMapping("/update/{postCode}")
	public String updatePost(Model model, @PathVariable("postCode")String postCode) {
		Post postItem = postRepository.selectByPostCode(postCode);
		model.addAttribute("post",postItem);
		return "/posts/update.html";
	}
	
	@PostMapping("/update/{postCode}")
	public String updatePostProcess(Model model, @PathVariable("postCode")String postCode, @ModelAttribute Post postItem) {
		postRepository.update(postCode, postItem);
		return "redirect:/posts/{postCode}";
	}
	
	
	
	
	
}
