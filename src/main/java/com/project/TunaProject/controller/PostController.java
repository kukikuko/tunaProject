package com.project.TunaProject.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import com.project.TunaProject.img.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.TunaProject.domain.Category;
import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.domain.Post;
import com.project.TunaProject.repository.CategoryRepository;
import com.project.TunaProject.repository.PostRepository;
import com.project.TunaProject.session.SessionManager;
import com.project.TunaProject.session.SessionVar;


import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

	private final PostRepository postRepository;
	private final CategoryRepository categoryRepository;
	private final SessionManager sessionManager;

	private final ItemRepository itemRepository;
	private final FileStore fileStore;
	
	@GetMapping
	public String posts(Model model, HttpServletRequest req) {
		List<Post> postList = postRepository.selectAll();
		model.addAttribute("posts", postList);
		return "/posts/posts";
	}
	
	@GetMapping("/{postCode}")
	public String post(Model model, @PathVariable("postCode") String postCode, @ModelAttribute("post") Post postItem
			, HttpServletRequest req) {
		postRepository.viewCont(postItem.getPostCode());
		postItem = postRepository.selectByPostCode(postCode);
		log.info("postItem {}", postItem);
		
		HttpSession session = req.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);
		log.info("memberVO {}", memberVO.getMemberCode());
		model.addAttribute("post",postItem);
		model.addAttribute("member", memberVO);
		return "/posts/post";
		
	}
	
	@GetMapping("/writing")
	public String postWriting(Model model) {
		
		List<Category> cateItem = categoryRepository.selectAll();
		model.addAttribute("post", new Post());
		model.addAttribute("cateItem", cateItem);
		
		return "/posts/writing";
	}
	
	@PostMapping("/writing")
	public String postWritingInsert(@ModelAttribute Post postItem
			, @ModelAttribute ItemForm form
			, @ModelAttribute Category ct
			, RedirectAttributes rAttr
			, HttpServletRequest req) throws IOException {

		HttpSession session = req.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);

		List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

		Item item = new Item();
		item.setItemName(form.getItemName());
		item.setImageFiles(storeImageFiles);
		itemRepository.save(item);

		log.info("img {}", item);
		log.info("img {}", item.getImageFiles().get(0));
		
		log.info("ct {}", ct);
		log.info("postItem {}", postItem);
		Post post = postRepository.insert(postItem, memberVO.getMemberCode(), ct.getCtCode());
		log.info("postItem {}", post);

		rAttr.addAttribute("postCode", post.getPostCode());

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

	@ResponseBody
	@GetMapping("/images/{filename}")
	public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
		return new UrlResource("file:" + fileStore.getFullPath(filename));
	}
}
