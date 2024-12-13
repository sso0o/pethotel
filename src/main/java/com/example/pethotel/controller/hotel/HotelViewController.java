package com.example.pethotel.controller.hotel;

import com.example.pethotel.dto.hotel.SearchHotelRequest;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.Room;
import com.example.pethotel.service.BookingService;
import com.example.pethotel.service.HotelService;
import com.example.pethotel.service.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HotelViewController {

    private final HotelService hotelService;
    private final RoomService roomService;
    private final BookingService bookingService;

    @GetMapping("/hotel")
    public String showHotelPage(HttpSession session, Model model) {
        SearchHotelRequest searchData = (SearchHotelRequest) session.getAttribute("searchData");

        if (searchData != null) {
           // 검색 조건을 모델에 추가하여 뷰에 전달
            model.addAttribute("searchData", searchData);

            // 세션에서 searchData를 삭제
            //session.removeAttribute("searchData");
        }
        return "hotel/hotelList";
    }

    @GetMapping("/hotel/hotelDetail/{hotelId}")
    public String showHotelDetailPage(@PathVariable Long hotelId, Model model) {
        Hotel hotel = hotelService.findById(hotelId);
        model.addAttribute("hotel", hotel);
        return "hotel/hotelDetail";
    }

    @GetMapping("/booking/{roomId}")
    public String makeBookingPage(@PathVariable Long roomId, Model model) {
        Room room = roomService.findById(roomId);
        Hotel hotel = hotelService.findById(room.getHotel().getHotelId());
        model.addAttribute("room", room);
        model.addAttribute("hotel", hotel);
        return "hotel/makeBooking";
    }

    @GetMapping("/booking/complete")
    public String bookingCompletePage(@RequestParam(required = false) String msg, Model model){
        model.addAttribute("msg", msg);
        return "hotel/bookingComplete";
    }

//    @GetMapping("/booking/complete")
//    public String bookingCompletePage(@RequestParam(required = false) String bookingId, @RequestParam(required = false) String resultCode,
//                                      @RequestParam(required = false) String resultMessage, @RequestParam(required = false) String reserveId,
//                                      @RequestParam(required = false) String paymentId, Model model){
//        if(bookingId!= null){
//            Booking booking = bookingService.findById(UUID.fromString(bookingId));
//            model.addAttribute("booking", booking);
//        }
//        model.addAttribute("bookingId", bookingId);
//        model.addAttribute("resultCode", resultCode);
//        model.addAttribute("resultMessage", resultMessage!= null? resultMessage : "No message provided");
//        model.addAttribute("paymentId", paymentId );
//        return "hotel/bookingComplete";
//    }
}
