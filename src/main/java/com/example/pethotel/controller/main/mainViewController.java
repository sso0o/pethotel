package com.example.pethotel.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainViewController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
