package com.example.pethotel.controller.hotel;

import com.example.pethotel.dto.hotel.SearchHotelRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HotelViewController {

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

    @GetMapping("/hotelDetail")
    public String showHotelDetailPage(Model model) {
        return "hotel/hotelDetail";
    }
}
