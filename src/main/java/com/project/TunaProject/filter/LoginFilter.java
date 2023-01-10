package com.project.TunaProject.filter;


import com.project.TunaProject.domain.MemberVO;
import com.project.TunaProject.session.SessionVar;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
	
	private static final String[] whiteList = {"/admin", "/admin/**"};

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest)request;
		String uri = req.getRequestURI();
		
		HttpServletResponse resp = (HttpServletResponse)response;
		
		if(PatternMatchUtils.simpleMatch(whiteList, uri)) {
			HttpSession session = req.getSession();
			MemberVO member = (MemberVO) session.getAttribute(SessionVar.LOGIN_MEMBER);
			if(session == null || session.getAttribute(SessionVar.LOGIN_MEMBER) == null ||
				!member.getMemberMail().equals("admin")) {
				log.info("로그인 없이 접근 시도 {}", uri);
				resp.sendRedirect("/");
				return;
			}

		}
		
		chain.doFilter(request, response);
	}

	
	
}
