package com.example.pethotel.controller.hotel;

import com.example.pethotel.dto.Criteria;
import com.example.pethotel.dto.hotel.*;
import com.example.pethotel.entity.*;
import com.example.pethotel.service.*;
import com.example.pethotel.service.payment.PaymentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class HotelApiController {

    private final HotelService hotelService;
    private final HotelImgService hotelImgService;
    private final RoomService roomService;
    private final BookingService bookingService;

    private final PaymentService paymentService;
    private final RoomImgService roomImgService;

    //=============================================================================================
    //================================              get               =============================
    //=============================================================================================

    // 검색 조건에 맞줘 호텔 목록 가져오기
//    @GetMapping("/hotel/search")
//    public ResponseEntity getHotelLists(@RequestParam("page") int page, @RequestParam("size") int size,
//                                         @RequestParam("location") String location,
//                                         @RequestParam("hotelType") String hotelType,
//                                         @RequestParam("checkIn") String checkIn,
//                                         @RequestParam("checkOut") String checkOut,
//                                         @RequestParam("room") int room,
//                                         @RequestParam("guest") int guest,
//                                         @RequestParam("pet") int pet) {
//        HashMap<Object, Object> resultMap = new HashMap<>();
//        location = (location == null || location.equals("전국")) ? "" : location;
//        SearchHotelRequest request = new SearchHotelRequest(location, hotelType, checkIn, checkOut, room, guest, pet);
//        Page<SearchHotelResponse> hotels = hotelService.findBySearchOption(request, page, size);
//        resultMap.put("hotels", hotels);
//        return ResponseEntity.ok().body(resultMap);
//    }

    // 검색 조건에 맞는 호텔 목록 가져오기
    @GetMapping("/hotel/SearchFilter")
    public ResponseEntity getSearchOption(@RequestParam("page") int page, @RequestParam("size") int size,
                                          @RequestParam("filter") String filter,
                                          @RequestParam("filterSize") int filterSize,
                                          @RequestParam("location") String location,
                                          @RequestParam("hotelType") String hotelType,
                                          @RequestParam("checkIn") String checkIn,
                                          @RequestParam("checkOut") String checkOut,
                                          @RequestParam("room") int room,
                                          @RequestParam("guest") int guest,
                                          @RequestParam("pet") int pet) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        location = (location == null || location.equals("전국")) ? "" : location;
        SearchHotelRequest request = new SearchHotelRequest(filter, filterSize, location, hotelType, checkIn, checkOut, room, guest, pet);
        Criteria criteria = new Criteria(page, size);
        List<SearchHotelResponse> result = hotelService.findBySearchFilter(request, criteria);
        resultMap.put("hotels", result);
        return ResponseEntity.ok().body(resultMap);
    }

    // 검색 조건에 맞는 객실 가져오기
    @GetMapping("/hotel/search/{hotelId}")
    public ResponseEntity getAvailableRoom(@PathVariable("hotelId") Long hotelId,
                                           @RequestParam("page") int page,
                                           @RequestParam("size") int size,
                                           @RequestParam("location") String location,
                                           @RequestParam("hotelType") String hotelType,
                                           @RequestParam("checkIn") String checkIn,
                                           @RequestParam("checkOut") String checkOut,
                                           @RequestParam("room") int room,
                                           @RequestParam("guest") int guest,
                                           @RequestParam("pet") int pet) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        String filter = null;
        int filterSize = 0;

        // SearchHotelRequest 객체 생성
        SearchHotelRequest request = new SearchHotelRequest(filter, filterSize, location, hotelType, checkIn, checkOut, room, guest, pet);

        Page<Room> rooms = roomService.findSearchRoom(hotelId, request, page, size);
        resultMap.put("rooms", rooms);
        return ResponseEntity.ok().body(resultMap);
    }

    // 검색 조건에 맞는 객실 가져오기
    @GetMapping("/hotel/searchRoom/{hotelId}")
    public ResponseEntity getAvailableRoomList(@PathVariable("hotelId") Long hotelId,
                                           @RequestParam("checkIn") String checkIn,
                                           @RequestParam("checkOut") String checkOut,
                                           @RequestParam("guest") int guest,
                                           @RequestParam("pet") int pet,
                                           @RequestParam("room") int room,
                                           @RequestParam("page") int page,
                                           @RequestParam("size") int size
    ) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        SearchHotelRoomRequest request = new SearchHotelRoomRequest(hotelId, checkIn, checkOut, guest, pet, room);
        Criteria criteria = new Criteria(page, size);
        List<SearchHotelRoomResponse> rooms = roomService.findAvailableRoomList(request, criteria);
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

    // 호텔 아이디별 사진 가져오기
    @GetMapping("/hotel/room/img/{roomId}")
    public ResponseEntity getRoomImg(@PathVariable Long roomId) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        Room room = roomService.findById(roomId);
        List<RoomImg> imgs = roomImgService.findByRoom(room);
        resultMap.put("roomPhotos", imgs);
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

    // 예약조건 저장
    @PostMapping("/booking/saveBookingData")
    public ResponseEntity saveBookingData(HttpSession session, @RequestBody AddBookingRequest req){
        HashMap<Object, Object> resultMap = new HashMap<>();
        int room = req.getRoom();
        List<Booking> bookings = new ArrayList<>();
        for(int i = 0; i < room; i++){
            Booking booking = bookingService.save(req);
            bookings.add(booking);
        }

        resultMap.put("booking", bookings);
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

    // 예약정보 저장
    @PostMapping("/booking/saveBooking")
    public ResponseEntity bookingSave(@RequestBody AddBookingRequest req){
        HashMap<Object, Object> resultMap = new HashMap<>();
        Booking booking = bookingService.save(req);
        resultMap.put("msg", "예약 요청 성공");
        resultMap.put("booking", booking);
        return ResponseEntity.ok().body(req);
    }


    //=============================================================================================
    //================================              put               =============================
    //=============================================================================================

    @PutMapping("/booking/paymentChk/{bookingId}")
    public ResponseEntity updatePaymentId(@PathVariable UUID bookingId,
                                          @RequestBody Map<String, String> payload){
        HashMap<Object, Object> resultMap = new HashMap<>();
        String paymentId = payload.get("paymentId");
        String payChk = payload.get("payChk");
        bookingService.updatePaycheck(bookingId, payChk, paymentId);
        resultMap.put("msg", "요청 성공");
        return ResponseEntity.ok().body(resultMap);

    }





}
