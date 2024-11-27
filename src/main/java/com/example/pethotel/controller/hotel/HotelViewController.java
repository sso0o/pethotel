package com.example.pethotel.controller.hotel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HotelViewController {

    @GetMapping("/hotel")
    public String showHotelPage(Model model) {
        return "hotel/hotelList";
    }

    @GetMapping("/hotelDetail")
    public String showHotelDetailPage(Model model) {
        return "hotel/hotelDetail";
    }
}
