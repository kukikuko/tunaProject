package com.project.TunaProject.controller;

import com.project.TunaProject.domain.Image;
import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.domain.Notify;
import com.project.TunaProject.domain.Post;
import com.project.TunaProject.repository.*;
import com.project.TunaProject.session.SessionVar;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/main")
    public String adminHome(Model model) {

        List<MemberVO> memberVOS = adminRepository.selectAll();
        log.info("member {}", memberVOS);
        model.addAttribute("members", memberVOS);

        return "/admin/home";
    }

    @GetMapping("/members")
    public String adminMembers(Model model) {

        List<MemberVO> memberVOS = adminRepository.selectAll();
        model.addAttribute("members", memberVOS);
        return "/admin/members";
    }

    @GetMapping("/member/{memberCode}")
    public String adminMember(Model model, @PathVariable("memberCode")int memberCode) {

        MemberVO memberVO = memberRepository.selectByCode(memberCode);
        List<Post> posts = memberRepository.selectByMemberCode(memberCode);

        model.addAttribute("member", memberVO);
        model.addAttribute("posts", posts);

        return "/admin/member";
    }

    @GetMapping("/notify/post")
    public String notifyPost(Model model) {

        List<Notify> notifyList = notifyRepository.selectNotifyAll("1");
        model.addAttribute("notifyList", notifyList);

        return "/admin/notifyPost";
    }

    @GetMapping("/notify/chat")
    public String notifyChat(Model model) {

        List<Notify> notifyList = notifyRepository.selectNotifyAll("2");
        model.addAttribute("notifyList", notifyList);

        return "/admin/notifyChat";
    }

    @GetMapping("/posts")
    public String posts(Model model){

        List<Post> posts = postRepository.selectAll();
        log.info("p {}", posts);
        model.addAttribute("posts", posts);

        return "/admin/posts";
    }

    @GetMapping("/post/{postCode}")
    public String posts(@PathVariable("postCode")String postCode, Model model) {

        Post post = postRepository.selectByPostCode(postCode);
        List<Image> images = imageRepository.selectAll(postCode);
        model.addAttribute("post", post);
        model.addAttribute("images", images);

        return "/admin/post";
    }
}

