package com.project.TunaProject.controller;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.repository.AdminRepository;
import com.project.TunaProject.repository.MemberRepository;
import com.project.TunaProject.repository.PostRepository;
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

        model.addAttribute("member", memberVO);

        return "/admin/member";
    }

    @GetMapping("/notify/post")
    public String notifyPost(Model model) {

        return "/admin/notifyPost";
    }
}

