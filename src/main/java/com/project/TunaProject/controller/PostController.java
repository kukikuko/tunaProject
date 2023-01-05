package com.project.TunaProject.controller;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.TunaProject.domain.Category;
import com.project.TunaProject.domain.Heart;
import com.project.TunaProject.domain.Image;
import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.domain.Post;
import com.project.TunaProject.form.ItemForm;
import com.project.TunaProject.img.FileStore;
import com.project.TunaProject.img.UploadFile;
import com.project.TunaProject.repository.CategoryRepository;
import com.project.TunaProject.repository.HeartRepository;
import com.project.TunaProject.repository.ImageRepository;
import com.project.TunaProject.repository.PostRepository;
import com.project.TunaProject.session.SessionManager;
import com.project.TunaProject.session.SessionVar;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	private final HeartRepository heartRepository;
	
	@GetMapping
	public String posts(Model model, HttpServletRequest req) {
		List<Post> postList = postRepository.selectAll();
		System.out.println(postList.size());
		HttpSession session = req.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);
		model.addAttribute("posts", postList);
		model.addAttribute("member", memberVO);
		log.info("posts {}" , postList);
		return "/posts/posts";
	}
	

	
	@GetMapping("/{postCode}")
	public String post(Model model, @PathVariable("postCode") String postCode, @ModelAttribute("post") Post postItem
			, HttpServletRequest req) {
		postRepository.viewCont(postItem.getPostCode());
		postItem = postRepository.selectByPostCode(postCode);
		HttpSession session = req.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);

		log.info("memberVO {}", memberVO.getMemberCode());
		
		//찜버튼 status를 전달할 수 있게
		Heart h = new Heart();
		h.setHMemCode(Integer.toString(memberVO.getMemberCode()));
		h.setHPostCode(postCode);
		int cnt = heartRepository.countHeart(h);
		System.out.println("*******************"+cnt);
		 
		
		List<Image> images = imageRepository.selectAll(postCode);
		
		log.info("img {}", images);

		model.addAttribute("images", images);
		model.addAttribute("post",postItem);
		model.addAttribute("member", memberVO);
		model.addAttribute("cnt123", cnt);
		
		return "/posts/post";
		
	}
	
	@GetMapping("/writing")
	public String postWriting(Model model, HttpServletRequest req) {
		
		List<Category> cateItem = categoryRepository.selectAll();
		HttpSession session = req.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);
		
		model.addAttribute("post", new Post());
		model.addAttribute("cateItem", cateItem);
		model.addAttribute("member", memberVO);
		
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
	public String updatePost(Model model, @PathVariable("postCode")String postCode, HttpServletRequest req) {
		Post postItem = postRepository.selectByPostCode(postCode);
		List<Category> cateItem = categoryRepository.selectAll();
		HttpSession session = req.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);
		log.info("p {}", postItem);
		if(postItem.getPSalesStatus().equals("Y")) {
			postItem.setPSalesStatus("판매중");
		}else if(postItem.getPSalesStatus().equals("S")) {
			postItem.setPSalesStatus("예약중");
		}else if(postItem.getPSalesStatus().equals("N")) {
			postItem.setPSalesStatus("판매완료");
		}
		model.addAttribute("post",postItem);
		model.addAttribute("cateItem", cateItem);
		model.addAttribute("member", memberVO);		
		return "/posts/update";
	}
	
	
	
	@PostMapping("/update/{postCode}")
	public String updatePostProcess(Model model, @PathVariable("postCode")String postCode, @ModelAttribute Post postItem,
			@ModelAttribute ItemForm form, @ModelAttribute Category ct) {
		
		log.info(postCode);
		log.info("ct {}", ct);
		
		System.out.println(postItem.getPSalesStatus());
		
		if(postItem.getPSalesStatus().equals("판매중")) {
			postItem.setPSalesStatus("Y");
		}else if(postItem.getPSalesStatus().equals("예약중")) {
			postItem.setPSalesStatus("S");
		}else if(postItem.getPSalesStatus().equals("판매완료")) {
			postItem.setPSalesStatus("N");
		}
		
		postRepository.update(postCode, postItem, ct.getCtCode());
		return "redirect:/posts/{postCode}";
	}
	
	
	@PostMapping("/delete")
	public String updateDeleteProcess(@ModelAttribute Post post) {
		log.info("postCode {}", post.getPostCode());

		postRepository.updateDelete(post.getPostCode());
		
		return "redirect:/posts";
	}

	@ResponseBody
	@GetMapping("/images/{filename}")
	public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
		log.info(filename);
		return new UrlResource("file:" + fileStore.getFullPath(filename));
	}
	
	@PostMapping
	public String selectSearch(Model model,HttpServletRequest req) {
		String keyword = req.getParameter("search");
		List<Post> postList = postRepository.selectSearch(keyword);
		HttpSession session = req.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);
		model.addAttribute("posts", postList);
		model.addAttribute("member", memberVO);
		return "/posts/posts";
	}
	
	@PostMapping("/notify")
	public String notifyPost(@RequestParam Map<String, String> postCode) {
		
		String a = postCode.get("postCode");
		System.out.println("****" + a + "****");
		
		return "redirect:/";
	}
	
}
