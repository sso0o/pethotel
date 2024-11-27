package com.example.pethotel.controller.hotel;

import com.example.pethotel.dto.hotel.SearchHotelRequest;
import com.example.pethotel.dto.hotel.SearchHotelResponse;
import com.example.pethotel.service.HotelService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HotelApiController {

    private final HotelService hotelService;

    //=============================================================================================
    //================================              get               =============================
    //=============================================================================================

    // 검색 조건에 맞줘 호텔 목록 가져오기
    @GetMapping("/hotel/search")
    public ResponseEntity getHotelLists(@RequestParam("page") int page, @RequestParam("size") int size) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        Page<SearchHotelResponse> hotels = hotelService.findBySearchOption(page, size);
        resultMap.put("hotels", hotels);
        return ResponseEntity.ok().body(resultMap);
    }

    // 세션에 있는 검색조건 가져오기
    @GetMapping("/hotel/getSearchOption")
    public ResponseEntity getHotelSearchOption(HttpSession session){
        HashMap<Object, Object> resultMap = new HashMap<>();
        SearchHotelRequest searchData = (SearchHotelRequest) session.getAttribute("searchData");
        if(searchData == null){
            searchData = new SearchHotelRequest();
        }

        resultMap.put("searchData", searchData);
        return ResponseEntity.ok().body(resultMap);

    }

    //=============================================================================================
    //================================             post               =============================
    //=============================================================================================

    // 호텔 검색조건 저장
    @PostMapping("/hotel/saveSearchOption")
    public ResponseEntity saveSearchOption(HttpSession session, @RequestBody SearchHotelRequest req){
        session.setAttribute("searchData", req);
        return ResponseEntity.ok().build();
    }




}
