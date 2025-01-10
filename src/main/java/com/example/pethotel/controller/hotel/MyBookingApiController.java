package com.example.pethotel.controller.hotel;

import com.example.pethotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MyBookingApiController {

    private final BookingService bookingService;

    @GetMapping("/mybooking/{userid}")
    public ResponseEntity<?> getMyBooking(@PathVariable Long userid) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> pendingPayment = bookingService.findPendingOrCancelByUserid(userid, "Booking");
        List<Map<String, Object>> paidBookings = bookingService.findPaidOrCompleteByUserid(userid, "paid");
        List<Map<String, Object>> completedBookings = bookingService.findPaidOrCompleteByUserid(userid, "Success");
        List<Map<String, Object>> canceledBookings = bookingService.findPendingOrCancelByUserid(userid, "Cancel");

        resultMap.put("pendingPayment", pendingPayment);
        resultMap.put("paidBookings", paidBookings);
        resultMap.put("completedBookings", completedBookings);
        resultMap.put("canceledBookings", canceledBookings);
        return ResponseEntity.ok().body(resultMap);
    }
}
