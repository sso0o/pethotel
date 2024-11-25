package com.example.pethotel.controller.admin;

import com.example.pethotel.entity.CommonCode;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.Room;
import com.example.pethotel.service.RoomService;
import com.example.pethotel.service.admin.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Controller
@RequiredArgsConstructor
public class AdminViewController {

    public final CommonCodeService commonCodeService;
    public final RoomService roomService;

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


}
