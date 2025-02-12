package com.example.pethotel.controller.hotel;

import com.example.pethotel.dto.hotel.SearchHotelRequest;
import com.example.pethotel.entity.*;
import com.example.pethotel.service.BookingService;
import com.example.pethotel.service.HotelService;
import com.example.pethotel.service.RoomService;
import com.example.pethotel.service.admin.CommonCodeService;
import com.example.pethotel.service.payment.PaymentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class HotelViewController {

    private final HotelService hotelService;
    private final RoomService roomService;
    private final BookingService bookingService;
    private final CommonCodeService commonCodeService;

    private final PaymentService paymentService;

    @GetMapping("/hotel")
    public String showHotelPage(HttpSession session, Model model) {
        SearchHotelRequest searchData = (SearchHotelRequest) session.getAttribute("searchData");

        if (searchData != null) {
           // 검색 조건을 모델에 추가하여 뷰에 전달
            model.addAttribute("searchData", searchData);
            // 세션에서 searchData를 삭제
            //session.removeAttribute("searchData");
        }
        List<CommonCode> REG = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("REG", "", "Y");
        List<CommonCode> HFC = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("HFC", "", "Y");
        model.addAttribute("REG", REG);
        model.addAttribute("HFC", HFC);
        return "hotel/hotelList";
    }

    @GetMapping("/hotel/hotelDetail/{hotelId}")
    public String showHotelDetailPage(@PathVariable Long hotelId, Model model) {
        Hotel hotel = hotelService.findById(hotelId);
        List<CommonCode> REG = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("REG", "", "Y");
        List<CommonCode> HFC = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike("HFC", "", "Y");
        model.addAttribute("hotel", hotel);
        model.addAttribute("REG", REG);
        model.addAttribute("HFC", HFC);
        return "hotel/hotelDetail";
    }

    @GetMapping("/myBooking")
    public String showMyBookingPage(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Long userId = user.getId();

        List<Map<String, Object>> pendingPayment = bookingService.findPendingOrCancelByUserid(userId, "Booking");
        List<Map<String, Object>> paidBookings = bookingService.findPaidOrCompleteByUserid(userId, "Paid");
        List<Map<String, Object>> completedBookings = bookingService.findPaidOrCompleteByUserid(userId, "Completed");
        List<Map<String, Object>> canceledBookings = bookingService.findPendingOrCancelByUserid(userId, "Cancel");


        model.addAttribute("pendingPayment", pendingPayment);
        model.addAttribute("paidBookings", paidBookings);
        model.addAttribute("completedBookings", completedBookings);
        model.addAttribute("canceledBookings", canceledBookings);
        return "myBooking/myBooking";
    }

    @GetMapping("/mypage")
    public String showMyPage(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "myPage/myPage";
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
    public String bookingCompletePage(@RequestParam(required = false) String bookingId, @RequestParam(required = false) String resultCode,
                                      @RequestParam(required = false) String resultMessage, @RequestParam(required = false) String paymentId, Model model){

        if(bookingId!= null && resultCode == null){
            Booking booking = bookingService.findById(UUID.fromString(bookingId));
            Hotel hotel = hotelService.findById(booking.getHotelId());
            Room room = roomService.findById(booking.getRoomId());
            model.addAttribute("booking", booking);
            model.addAttribute("hotel", hotel);
            model.addAttribute("room", room);
        }

        if(resultCode != null && resultCode.equals("Success")) {
            //결제 승인 처리
            Map<String, Object> paymentResult = paymentService.nPayProgress(paymentId);
            bookingService.updatePaycheck(UUID.fromString(bookingId), "Paid", paymentId);
        }
        model.addAttribute("bookingId", bookingId);
        model.addAttribute("resultCode", resultCode);
        model.addAttribute("resultMessage", resultMessage!= null? resultMessage : "No message provided");
        model.addAttribute("paymentId", paymentId );
        return "hotel/bookingComplete";

    }
}
