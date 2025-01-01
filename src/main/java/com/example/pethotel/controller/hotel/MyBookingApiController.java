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
        List<Map<String, Object>> paidBookings = bookingService.findByUserid(userid, "paid");
        List<Map<String, Object>> waitingBookings = bookingService.findByUserid(userid, "Success");
        List<Map<String, Object>> cancelBookings = bookingService.findByUserid(userid, "cancel");

        resultMap.put("paidBookings", paidBookings);
        resultMap.put("waitingBookings", waitingBookings);
        resultMap.put("cancelBookings", cancelBookings);
        return ResponseEntity.ok().body(resultMap);
    }
}
