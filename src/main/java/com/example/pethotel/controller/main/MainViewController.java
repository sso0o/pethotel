package com.example.pethotel.controller.main;

import com.example.pethotel.entity.CommonCode;
import com.example.pethotel.service.HotelService;
import com.example.pethotel.service.admin.CommonCodeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainViewController {

    public final CommonCodeService commonCodeService;
    public final HotelService hotelService;

    @GetMapping("/")
    public String index(Model model) {
        List<CommonCode> REG = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("REG", "", "Y");
        model.addAttribute("REG", REG);
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
