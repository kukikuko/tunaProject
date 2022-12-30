package com.project.TunaProject.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/400")
    public String error400(HttpServletRequest req, HttpServletResponse resp) {
        log.info("error 401");
        return "error/400";
    }

    @RequestMapping("/404")
    public String error404(HttpServletRequest req, HttpServletResponse resp) {
        log.info("error 404");
        return "error/404";
    }

    @RequestMapping("/500")
    public String error500(HttpServletRequest req, HttpServletResponse resp) {
        log.info("error 500");
        return "error/500";
    }

}
