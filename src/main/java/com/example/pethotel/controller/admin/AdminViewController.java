package com.example.pethotel.controller.admin;

import com.example.pethotel.service.admin.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminViewController {

    public final CommonCodeService commonCodeService;

    @GetMapping("/admin")
    public String admin() {
        return "admin/main";
    }

    @GetMapping("/admin/commoncodePage")
    public String commoncode() {
        return "admin/commoncode";
    }

    @GetMapping("/admin/managerPage")
    public String manager() {
        return "admin/manager";
    }

    @GetMapping("/admin/customerPage")
    public String customer() {
        return "admin/customer";
    }

    @GetMapping("/manager/hotelmainPage")
    public String hotelMainPage() {
        return "manager/myHotelMain";
    }

    @GetMapping("/manager/myhotelPage")
    public String hotelPage() {
        return "manager/myHotel";
    }
}
