package com.example.pethotel.controller.hotel;

import com.example.pethotel.dto.hotel.SearchHotelRequest;
import com.example.pethotel.dto.hotel.SearchHotelResponse;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.HotelImg;
import com.example.pethotel.entity.Room;
import com.example.pethotel.service.HotelImgService;
import com.example.pethotel.service.HotelService;
import com.example.pethotel.service.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HotelApiController {

    private final HotelService hotelService;
    private final HotelImgService hotelImgService;
    private final RoomService roomService;

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

    // 검색 조건에 맞는 객실 가져오기
    @GetMapping("/hotel/search/{hotelId}")
    public ResponseEntity getAvailableRoom(@PathVariable("hotelId") Long hotelId, @RequestParam("page") int page, @RequestParam("size") int size) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        Page<Room> rooms = roomService.findBySearchOption(hotelId, page, size);
        resultMap.put("rooms", rooms);
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

    // 호텔 아이디별 사진 가져오기
    @GetMapping("/hotel/img/{hotelId}")
    public ResponseEntity getHotelImg(@PathVariable Long hotelId) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        Hotel hotel = hotelService.findById(hotelId);
        List<HotelImg> imgs = hotelImgService.findByHotel(hotel);
        resultMap.put("hotelPhotos", imgs);
        return ResponseEntity.ok().body(resultMap);
    }



    //=============================================================================================
    //================================             post               =============================
    //=============================================================================================

    // 호텔 검색조건 저장
    @PostMapping("/hotel/saveSearchOption")
    public ResponseEntity saveSearchOption(HttpSession session, @RequestBody SearchHotelRequest req){
        session.setAttribute("searchData", req);
        return ResponseEntity.ok().body(req);
    }




}
