package com.example.pethotel.controller.main;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainViewController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        String uri = request.getHeader("Referer");
        if(!uri.contains("/login")){
            request.getSession().setAttribute("prevPage", uri);
        }
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}
