package com.example.pethotel.controller.hotel;

import com.example.pethotel.dto.hotel.SearchHotelRequest;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.service.HotelService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HotelViewController {

    private final HotelService hotelService;

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
}
