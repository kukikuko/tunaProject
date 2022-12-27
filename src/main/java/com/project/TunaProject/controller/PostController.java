package com.project.TunaProject.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.project.TunaProject.domain.*;
import com.project.TunaProject.img.*;
import com.project.TunaProject.repository.ImageRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.TunaProject.repository.CategoryRepository;
import com.project.TunaProject.repository.PostRepository;
import com.project.TunaProject.session.SessionManager;
import com.project.TunaProject.session.SessionVar;


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
	private final ImageRepository imageRepository;
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

		List<Image> images = imageRepository.selectAll(postCode);

		log.info("img {}", images);

		model.addAttribute("images", images);
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

		Post post = postRepository.insert(postItem, memberVO.getMemberCode(), ct.getCtCode());

		rAttr.addAttribute("postCode", post.getPostCode());

		List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

		for(UploadFile uf : storeImageFiles) {
			Image img = new Image();
			img.setImageName(uf.getUploadFileName());
			img.setImageFiles(uf.getStoreFileName());
			img.setITarget("1");
			img.setIType(post.getPostCode());
			imageRepository.insert(img);

		}

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
		log.info(filename);
		return new UrlResource("file:" + fileStore.getFullPath(filename));
	}
}
