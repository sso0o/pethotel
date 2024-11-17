package com.example.pethotel.controller.admin;

import com.example.pethotel.entity.CommonCode;
import com.example.pethotel.service.admin.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminViewController {

    public final CommonCodeService commonCodeService;

    @GetMapping("/admin")
    public String admin() {
        return "admin/main";
    }

    @GetMapping("/admin/commoncode")
    public String commoncode(Model model) {
        List<CommonCode> codeHeads = commonCodeService.findAll().stream()
                .toList();
        model.addAttribute("codeHeads", codeHeads);
        return "admin/commoncode";
    }
}
