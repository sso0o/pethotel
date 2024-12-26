package com.example.pethotel.controller.manager;

import com.example.pethotel.entity.CommonCode;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.Room;
import com.example.pethotel.service.BookingService;
import com.example.pethotel.service.HotelService;
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
    public final BookingService bookingService;
    public final HotelService hotelService;

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

        return "manager/myHotelRoom";
    }

    @GetMapping("/manager/myhotelroomdetail/{userId}/{hotelId}/{roomId}")
    public String myHotelRoomDetailPage(Model model, @PathVariable Long userId, @PathVariable Long hotelId, @PathVariable Long roomId) {
        List<Hotel> hotels = hotelService.findAllByUserId(userId);
        model.addAttribute("hotels", hotels);
        if (hotelId!= 0) {
            Hotel hotel = hotelService.findById(hotelId);
            model.addAttribute("hotel", hotel);
        }
        if (roomId != 0) {
            Room room = roomService.findById(roomId);
            model.addAttribute("room", room);
        }

        List<CommonCode> RAM = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("RAM", "", "Y");
        List<CommonCode> RFT = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("RFT", "", "Y");
        List<CommonCode> RFT01 = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("RFT01", "", "Y");
        List<CommonCode> RFT02 = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("RFT02", "", "Y");
        List<CommonCode> RFT03 = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("RFT03", "", "Y");
        List<CommonCode> RFT04 = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("RFT04", "", "Y");
        List<CommonCode> RFT05 = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("RFT05", "", "Y");
        List<CommonCode> RFT06 = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("RFT06", "", "Y");
        List<CommonCode> RFT07 = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("RFT07", "", "Y");

        model.addAttribute("RAM", RAM);
        model.addAttribute("RFT", RFT);
        model.addAttribute("RFT01", RFT01);
        model.addAttribute("RFT02", RFT02);
        model.addAttribute("RFT03", RFT03);
        model.addAttribute("RFT04", RFT04);
        model.addAttribute("RFT05", RFT05);
        model.addAttribute("RFT06", RFT06);
        model.addAttribute("RFT07", RFT07);


        return "manager/myHotelRoomDetail";
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
