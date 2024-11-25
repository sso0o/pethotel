package com.example.pethotel.controller.manager;

import com.example.pethotel.entity.CommonCode;
import com.example.pethotel.entity.Room;
import com.example.pethotel.service.RoomService;
import com.example.pethotel.service.admin.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ManagerViewController {

    public final CommonCodeService commonCodeService;
    public final RoomService roomService;

    @GetMapping("/manager/hotelmainPage")
    public String hotelMainPage() {
        return "manager/myHotelMain";
    }

    @GetMapping("/manager/myhotelPage")
    public String hotelPage(Model model) {
        List<CommonCode> HTP = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("HTP", "", "Y");
        model.addAttribute("HTP", HTP);
        return "manager/myHotel";
    }

    @GetMapping("/manager/myhotelroom/{hotelId}")
    public String myHotelRoom(Model model, @PathVariable Long hotelId) {
        if (hotelId != 0) {
            List<Room> rooms = roomService.findAllByHotelId(hotelId);
            model.addAttribute("rooms", rooms);
        }

        return "manager/myHotelRoom";
    }
}
