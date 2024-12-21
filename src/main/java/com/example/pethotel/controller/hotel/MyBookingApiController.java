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
    public ResponseEntity<?> getMyBooking(@PathVariable Long userid){
        HashMap<Object, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> myBookings = bookingService.findByUserid(userid);
        resultMap.put("myBookings", myBookings);
        return ResponseEntity.ok().body(resultMap);
    }
}
