package com.project.TunaProject.controller;

import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.domain.Post;
import com.project.TunaProject.repository.PostRepository;
import com.project.TunaProject.session.SessionManager;
import com.project.TunaProject.session.SessionVar;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Enumeration;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostRepository postRepository;
    private final SessionManager sessionManager;

    @GetMapping("/")
    public String home(Model model, HttpServletRequest req) {

        HttpSession session = req.getSession(false);
        if(session == null) {
            return "index";
        }

        Enumeration<String> sessionName = session.getAttributeNames();
        while(sessionName.hasMoreElements()) {
            String name = sessionName.nextElement();
            log.info("session {}, {}", name, session.getAttribute(name));
        }

        log.info("{}, {}, {}, {}, {}", session.getId(), session.getMaxInactiveInterval(),
                session.getCreationTime(), session.getLastAccessedTime(), session.isNew());

        MemberVO memberVO = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);
        if(memberVO == null) {
            return "index";
        }
        List<Post> postList = postRepository.selectAll();
        model.addAttribute("posts", postList);
        model.addAttribute("memberVO", memberVO);
        log.info("Login memberInfo {}", memberVO);

        return "/posts/posts";
    }
}
