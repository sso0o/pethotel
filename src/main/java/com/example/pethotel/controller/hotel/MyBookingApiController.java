package com.example.pethotel.controller.hotel;

import com.example.pethotel.entity.Booking;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.Room;
import com.example.pethotel.service.BookingService;
import com.example.pethotel.service.HotelService;
import com.example.pethotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MyBookingApiController {

    private final BookingService bookingService;
    private final HotelService hotelService;
    private final RoomService roomService;

//    @GetMapping("/mybooking/{userid}")
//    public ResponseEntity<?> getMyBooking(@PathVariable Long userid) {
//        HashMap<Object, Object> resultMap = new HashMap<>();
//        List<Map<String, Object>> pendingPayment = bookingService.findPendingOrCancelByUserid(userid, "Booking");
//        List<Map<String, Object>> paidBookings = bookingService.findPaidOrCompleteByUserid(userid, "paid");
//        List<Map<String, Object>> completedBookings = bookingService.findPaidOrCompleteByUserid(userid, "Success");
//        List<Map<String, Object>> canceledBookings = bookingService.findPendingOrCancelByUserid(userid, "Cancel");
//
//        resultMap.put("pendingPayment", pendingPayment);
//        resultMap.put("paidBookings", paidBookings);
//        resultMap.put("completedBookings", completedBookings);
//        resultMap.put("canceledBookings", canceledBookings);
//        return ResponseEntity.ok().body(resultMap);
//    }

    @GetMapping("/mybooking/{bookingId}")
    public ResponseEntity<?> getBooking(@PathVariable UUID bookingId) {
        HashMap<Object, Object> resultMap = new HashMap<>();
//        Booking booking = bookingService.findById(UUID.fromString(bookingId));
        Booking booking = bookingService.findById(bookingId);
        Hotel hotel = hotelService.findById(booking.getHotelId());
        Room room = roomService.findById(booking.getRoomId());
        resultMap.put("booking", booking);
        resultMap.put("hotel", hotel);
        resultMap.put("room", room);
        return ResponseEntity.ok().body(resultMap);
    }

    @PutMapping("/mybooking/cancel/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable UUID bookingId) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        bookingService.updateCancel(bookingId, "Cancel");
        resultMap.put("msg", "요청 성공");
        return ResponseEntity.ok().body(resultMap);
    }

    //    // nPay 승인 로직
//    @PostMapping("/booking/approve/{paymentId}")
//    public ResponseEntity bookingApprove(@PathVariable String paymentId, @RequestBody Booking booking){
//        HashMap<Object, Object> resultMap = new HashMap<>();
//        Map<String, Object> paymentResult = paymentService.nPayProgress(paymentId);
//        if(paymentResult.get("code").equals("Success")){
//            bookingService.updatePaycheck(booking.getBookingId(), "paid", paymentId);
//            resultMap.put("booking", booking);
//        }
//
//        resultMap.put("paymentResult", paymentResult);
//        return ResponseEntity.ok().body(resultMap);
//    }
}
