package com.example.pethotel.controller.manager;

import com.example.pethotel.entity.CommonCode;
import com.example.pethotel.entity.Room;
import com.example.pethotel.service.BookingService;
import com.example.pethotel.service.RoomService;
import com.example.pethotel.service.admin.CommonCodeService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public final BookingService bookingService;

    @GetMapping("/manager/hotelmainPage")
    public String hotelMainPage() {
        return "manager/myHotelMain";
    }

    @GetMapping("/manager/myhotelPage")
    public String hotelPage(Model model) {
        List<CommonCode> HTP = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("HTP", "", "Y");
        List<CommonCode> REG = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("REG", "", "Y");
        List<CommonCode> HFC = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("HFC", "", "Y");
        model.addAttribute("HTP", HTP);
        model.addAttribute("REG", REG);
        model.addAttribute("HFC", HFC);

        return "manager/myHotel";
    }

    @GetMapping("/manager/myhotelroom/{hotelId}")
    public String myHotelRoom(Model model, @PathVariable Long hotelId) {
        if (hotelId != 0) {
            List<Room> rooms = roomService.findAllByHotelId(hotelId);
            model.addAttribute("rooms", rooms);
        }

        List<CommonCode> RAM = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("RAM", "", "Y");
        List<CommonCode> RFT = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("RFT", "", "Y");
        List<CommonCode> RFT01 = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("RFT01", "", "Y");
        List<CommonCode> RFT02 = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("RFT02", "", "Y");
        model.addAttribute("RAM", RAM);
        model.addAttribute("RFT", RFT);
        model.addAttribute("RFT01", RFT01);
        model.addAttribute("RFT02", RFT02);

        try {
            // CommonCode 리스트를 JSON 문자열로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            String RFTJson = objectMapper.writeValueAsString(RFT);

            model.addAttribute("RFTJson", RFTJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "manager/myHotelRoom";
    }

    @GetMapping("/manager/myhotelbooking/{hotelId}")
    public String myHotelBooking(Model model, @PathVariable Long hotelId) {
        if (hotelId!= 0) {
            List<Room> rooms = roomService.findAllByHotelId(hotelId);
            model.addAttribute("rooms", rooms);
        }
        return "manager/myHotelBooking";
    }

    @GetMapping("manager/myhotelrequest/{hotelId}")
    public String myHotelRequest(Model model, @PathVariable Long hotelId) {
        return "manager/myHotelRequest";
    }

}
