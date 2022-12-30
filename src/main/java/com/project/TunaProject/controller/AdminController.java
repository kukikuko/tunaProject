package com.project.TunaProject.controller;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository adminRepository;

    @GetMapping("/main")
    public String adminHome(Model model) {

        List<MemberVO> memberVOS = adminRepository.selectAll();
        log.info("member {}", memberVOS);
        model.addAttribute("members", memberVOS);

        return "/admin/home";
    }

    @GetMapping("/member")
    public String adminMember() {
        return "/admin/member";
    }

}

