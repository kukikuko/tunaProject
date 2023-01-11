package com.project.TunaProject.controller;

import com.project.TunaProject.domain.*;
import com.project.TunaProject.repository.*;
import com.project.TunaProject.session.SessionVar;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import jdk.jfr.Relational;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final NotifyRepository notifyRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/main")
    public String adminHome(Model model) {

        List<MemberVO> memberVOS = adminRepository.selectAll();
        log.info("member {}", memberVOS);
        model.addAttribute("members", memberVOS);

        return "admin/home";
    }

    @GetMapping("/members")
    public String adminMembers(Model model) {

        List<MemberVO> memberVOS = adminRepository.selectAll();
        model.addAttribute("members", memberVOS);
        return "admin/members";
    }

    @GetMapping("/member/{memberCode}")
    public String adminMember(Model model, @PathVariable("memberCode")int memberCode) {

        MemberVO memberVO = memberRepository.selectByCode(memberCode);
        List<Post> posts = memberRepository.selectByMemberCode(memberCode);

        model.addAttribute("member", memberVO);
        model.addAttribute("posts", posts);

        return "admin/member";
    }

    @GetMapping("/notify/post")
    public String notifyPost(Model model) {

        List<Notify> notifyList = notifyRepository.selectNotifyAll("1");
        model.addAttribute("notifyList", notifyList);

        return "admin/notifyPost";
    }

    @GetMapping("/notify/chat")
    public String notifyChat(Model model) {

        List<Notify> notifyList = notifyRepository.selectNotifyAll("2");
        model.addAttribute("notifyList", notifyList);

        return "admin/notifyChat";
    }

    @GetMapping("/posts")
    public String posts(Model model){

        List<Post> posts = postRepository.selectAll();
        log.info("p {}", posts);
        model.addAttribute("posts", posts);

        return "admin/posts";
    }

    @GetMapping("/post/{postCode}")
    public String post(@PathVariable("postCode")String postCode, Model model) {

        Post post = postRepository.selectByPostCode(postCode);
        List<Image> images = imageRepository.selectAll(postCode);
        model.addAttribute("post", post);
        model.addAttribute("images", images);

        return "admin/post";
    }

    @ResponseBody
    @PostMapping("/member/status")
    public void memberStatus(@RequestParam("memCode") int memCode
            , @RequestParam("status") String status) {

        adminRepository.memberStatus(memCode, status);

    }

    @ResponseBody
    @PostMapping("/post/status")
    public void postStatus(@RequestParam("postCode") String postCode
            , @RequestParam("status") String status) {

        adminRepository.postStatus(postCode, status);
    }

    @GetMapping("/category")
    public String category(Model model) {

        model.addAttribute("categories", adminRepository.categoryAll());

        return "admin/category";
    }

    @ResponseBody
    @PostMapping("/category")
    public void categoryChange(@RequestParam("ctCode") String ctCode
                ,@RequestParam("ctName") String ctName){
        log.info("category {} {}", ctCode, ctName);
        categoryRepository.updateCtName(new Category(ctCode, ctName));
    }

    @ResponseBody
    @PostMapping("/category/insert")
    public void categoryInsert(@RequestParam("ctName") String ctName){
        categoryRepository.insertCategory(ctName);
    }
}

