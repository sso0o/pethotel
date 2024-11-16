package com.example.pethotel.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminViewController {

    @GetMapping("/admin")
    public String admin() {
        return "admin/main";
    }

    @GetMapping("/admin/commoncode")
    public String commoncode() {
        return "admin/commoncode";
    }
}
