package com.example.pethotel.controller.manager;


import com.example.pethotel.dto.manager.*;
import com.example.pethotel.entity.*;
import com.example.pethotel.service.*;
import com.example.pethotel.service.admin.ManagerService;
import com.example.pethotel.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ManagerApiController {

    private final ManagerService managerService;
    private final HotelService hotelService;
    private final HotelImgService hotelImgService;
    private final RoomService roomService;
    private final RoomImgService roomImgService;
    private final BookingService bookingService;

    private final CommonService commonService;
    private final FileService fileService;
    private final RoomDetailService roomDetailService;
    private final PaymentService paymentService;

    //=============================================================================================
    //================================              get               =============================
    //=============================================================================================

    // 매니저 아이디로 호텔 조회
    @GetMapping("/manager/myhotels/{userid}")
    public ResponseEntity getMyHotels(@PathVariable Long userid) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        List<Hotel> hotels = hotelService.findAllByUserId(userid);
        resultMap.put("hotels", hotels);
        return ResponseEntity.ok().body(resultMap);
    }

    // 호텔 아이디로 객실 조회
    @GetMapping("/manager/myrooms/{hotelId}")
    public ResponseEntity getMyRooms(@PathVariable Long hotelId) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        List<Room> rooms = roomService.findAllByHotelId(hotelId);
        resultMap.put("rooms", rooms);
        return ResponseEntity.ok().body(resultMap);
    }


    // 객실 타입으로 디테일 조회
    @GetMapping("/manager/myroomdetail/{roomId}")
    public ResponseEntity getMyRoomDetail(@PathVariable Long roomId) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        List<RoomDetail> roomDetail = roomDetailService.findAllByRoomId(roomId);
        resultMap.put("roomdetails", roomDetail);
        return ResponseEntity.ok().body(resultMap);
    }

    // 호텔 아이디로 호텔 조회
    @GetMapping("/manager/myhotel/{hotelId}")
    public ResponseEntity getMyHotel(@PathVariable Long hotelId) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        Hotel hotel = hotelService.findById(hotelId);
        resultMap.put("hotel", hotel);
        return ResponseEntity.ok().body(resultMap);
    }

    // 객실 아이디로 객실 조회
    @GetMapping("/manager/myroom/{roomId}")
    public ResponseEntity getMyRoom(@PathVariable Long roomId) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        Room room = roomService.findById(roomId);
        resultMap.put("room", room);
        return ResponseEntity.ok().body(resultMap);
    }

    // 호텔 아이디별 예약 조회
    @GetMapping("/manager/mybooking/{hotelId}")
    public ResponseEntity getMyBooking(@PathVariable Long hotelId) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        List<HotelBookingResponse> bookings = bookingService.findBookingResponseByHotelId(hotelId);
        resultMap.put("bookings", bookings);
        return ResponseEntity.ok().body(resultMap);
    }

    @GetMapping("/manager/myrequest/{hotelId}")
    public ResponseEntity getMyRequest(@PathVariable Long hotelId) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        List<HotelRequestResponse> bookings = bookingService.findAllByHotelIdAndPayChk(hotelId);
        resultMap.put("bookings", bookings);
        return ResponseEntity.ok().body(resultMap);
    }

    @GetMapping("/manager/roomtype/{hotelId}")
    public ResponseEntity getMyHotelRoom(@PathVariable Long hotelId) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        Hotel hotel = hotelService.findById(hotelId);
        List<Room> rooms = roomService.findAllByHotel(hotel);
        resultMap.put("roomTypes", rooms);
        return ResponseEntity.ok().body(resultMap);
    }

    @GetMapping("/manager/myroomdetail/booking/{roomId}")
    public ResponseEntity getMyRoomDetailBooking(@PathVariable Long roomId, @RequestParam(required = false) String bookingId) {
        HashMap<Object, Object> resultMap = new HashMap<>();

        LocalDate startDate;
        LocalDate endDate;
        if (bookingId != null) {
            Booking booking = bookingService.findById(UUID.fromString(bookingId));
//            Booking booking = bookingService.findById(bookingId);
            startDate = LocalDate.parse(booking.getStartDate()).minusDays(1); // 하루 전 날짜로 설정
            endDate = LocalDate.parse(booking.getEndDate());
            // 만약 예약 기간이 7일이 아니면, endDate를 6일로 설정
            if (endDate.toEpochDay() - startDate.toEpochDay() <= 7) {
                endDate = startDate.plusDays(6);
            }else{
                endDate = endDate.plusDays(2);
            }

        } else {
            // bookingId가 없을 경우 현재 날짜부터 7일간의 범위를 설정
            startDate = LocalDate.now();
            endDate = startDate.plusDays(6);
        }

        List<LocalDate> dateRange = new ArrayList<>();
        // 시작일에서 끝일까지 하루씩 증가시키며 날짜를 추가
        while (!startDate.isAfter(endDate)) {
            dateRange.add(startDate);
            startDate = startDate.plusDays(1);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDateStr = dateRange.get(0).format(formatter);
        String endDateStr = dateRange.get(dateRange.size() - 1).format(formatter);

        Map<String, Object> paidBookings = bookingService.findRoomBookingStatus(roomId, startDateStr, endDateStr);

        resultMap.put("dates", dateRange.stream()
                .map(d -> d.format(DateTimeFormatter.ISO_DATE))
                .collect(Collectors.toList()));
        resultMap.put("paidBookings", paidBookings);

        return ResponseEntity.ok().body(resultMap);
    }



    //=============================================================================================
    //================================              post              =============================
    //=============================================================================================

    // 매니저가 호텔 저장
    @PostMapping("/manager/myhotel")
    public ResponseEntity saveHotel(@RequestBody AddHotelRequest request) throws Exception {
        // 필수 항목들 체크
        commonService.checkRequiredField(request.getHotelName(), "호텔 이름은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getLocation(), "호텔 지역은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getPostcode(), "호텔 우편번호는 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getAddress(), "호텔 주소는 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getHotelPhone(), "호텔 번호는 필수 입력 항목입니다.");

        HashMap<Object, Object> resultMap = new HashMap<>();

        Hotel saveHotel = hotelService.save(request);
        resultMap.put("hotel", saveHotel);
        resultMap.put("msg", "요청 성공");
        return ResponseEntity.ok().body(resultMap);
    }

    // 매니저가 객실 저장
    @PostMapping("/manager/myroom")
    public ResponseEntity saveRoom(@RequestBody AddRoomRequest request) throws Exception {
        // 필수 항목들 체크
        commonService.checkRequiredField(request.getRoomType(), "객실 타입은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getRoomPrice(), "객실 가격은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getLimitGuest(), "최대 인원은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getLimitPet(), "최대 펫 수는 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getCheckIn(), "체크인 시간은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getCheckOut(), "체크아웃 시간은 필수 입력 항목입니다.");

        HashMap<Object, Object> resultMap = new HashMap<>();

        Room saveRoom = roomService.save(request);

        resultMap.put("msg", "요청 성공");
        resultMap.put("room", saveRoom);

        return ResponseEntity.ok().body(resultMap);

    }

    // 매니저가 호텔 사진 저장
    @PostMapping("/manager/myhotelImg/{hotelId}")
    public ResponseEntity saveHotelImg(@PathVariable Long hotelId,
                                       @RequestParam(value = "hotelPhotos") MultipartFile[] hotelPhotos)  throws IOException {
        HashMap<Object, Object> resultMap = new HashMap<>();

        Hotel hotel = hotelService.findById(hotelId);

        // 파일이 존재하면 처리
        if (hotelPhotos != null && hotelPhotos.length > 0) {
            String uploadUrl = "./uploads/hotel";

            List<String> fileNames = fileService.saveFiles(uploadUrl,hotelPhotos);

            for (String fileName : fileNames) {
                AddHotelImgRequest imgr = new AddHotelImgRequest(hotel, fileName, uploadUrl+"/"+fileName);
                HotelImg hotelImg = hotelImgService.save(imgr);
            }
        }

        resultMap.put("msg", "요청 성공");
        return ResponseEntity.ok().body(resultMap);

    }

    // 매니저가 객실 사진 저장
    @PostMapping("/manager/myroomImg/{roomId}")
    public ResponseEntity saveRoomImg(@PathVariable Long roomId,
                                      @RequestParam(value = "roomPhotos") MultipartFile[] roomPhotos)  throws IOException {
        HashMap<Object, Object> resultMap = new HashMap<>();

        Room room = roomService.findById(roomId);
        if (roomPhotos != null && roomPhotos.length > 0) {
            String uploadUrl = "./uploads/room";
            List<String> fileNames = fileService.saveFiles(uploadUrl,roomPhotos);
            for (String fileName : fileNames) {
                AddRoomImgRequest imgr = new AddRoomImgRequest(room, fileName, uploadUrl+"/"+fileName);
                RoomImg roomImg = roomImgService.save(imgr);
            }
        }
        resultMap.put("msg", "요청 성공");
        return ResponseEntity.ok().body(resultMap);
    }

    // 매니저가 객실 상세 저장
    @PostMapping("/manager/myroomdetail/{roomId}")
    public ResponseEntity saveRoomDetail(@PathVariable Long roomId, @RequestBody String roomName) throws Exception {
        // 필수 항목들 체크
        commonService.checkRequiredField(roomName, "객실 이름은 필수 입력 항목입니다.");

        HashMap<Object, Object> resultMap = new HashMap<>();
        Room room = roomService.findById(roomId);
        AddRoomDetailRequest request = new AddRoomDetailRequest(room, roomName);

        RoomDetail roomDetail = roomDetailService.save(request);

        resultMap.put("msg", "요청 성공");
        resultMap.put("room", roomDetail);

        return ResponseEntity.ok().body(resultMap);

    }





    //=============================================================================================
    //================================              put               =============================
    //=============================================================================================

    // 매니저가 호텔 수정
    @PutMapping("/manager/myhotel/{hotelId}")
    public ResponseEntity updateHotel(@PathVariable Long hotelId, @RequestBody UpdateHotelRequest request) throws Exception {
        // 필수 항목들 체크
        commonService.checkRequiredField(request.getHotelName(), "호텔 이름은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getLocation(), "호텔 지역은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getPostcode(), "호텔 우편번호는 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getAddress(), "호텔 주소는 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getHotelPhone(), "호텔 번호는 필수 입력 항목입니다.");

        HashMap<Object, Object> resultMap = new HashMap<>();

        Hotel updateHotel = hotelService.update(hotelId, request);
        resultMap.put("msg", "요청 성공");
        resultMap.put("hotel", updateHotel);

        return ResponseEntity.ok().body(resultMap);
    }

    // 매니저가 객실 수정
    @PutMapping("/manager/myroom/{roomId}")
    public ResponseEntity updateRoom(@PathVariable Long roomId, @RequestBody UpdateRoomRequest request) throws Exception {
        // 필수 항목들 체크
        commonService.checkRequiredField(request.getRoomType(), "객실 타입은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getRoomPrice(), "객실 가격은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getLimitGuest(), "최대 인원은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getLimitPet(), "최대 펫 수는 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getCheckIn(), "체크인 시간은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getCheckOut(), "체크아웃 시간은 필수 입력 항목입니다.");

        HashMap<Object, Object> resultMap = new HashMap<>();

        Room updateRoom = roomService.update(roomId, request);
        resultMap.put("msg", "요청 성공");
        resultMap.put("room", updateRoom);
        return ResponseEntity.ok().body(resultMap);
    }

    // 매니저가 객실 이름 수정
    @PutMapping("/manager/myroomdetail/{roomDetailId}")
    public ResponseEntity updateRoomDetail(@PathVariable Long roomDetailId, @RequestBody String roomName) throws Exception {
        // 필수 항목들 체크
        commonService.checkRequiredField(roomName, "객실 이름은 필수 입력 항목입니다.");

        HashMap<Object, Object> resultMap = new HashMap<>();

        RoomDetail updateRoomDetail = roomDetailService.update(roomDetailId, roomName);
        resultMap.put("msg", "요청 성공");
        resultMap.put("roomDetail", updateRoomDetail);
        return ResponseEntity.ok().body(resultMap);

    }

    // 매니저가 예약대기건 승인(객실 배정 및 결제승인요청 + 중복체크
    @PutMapping("/manager/booking/approve/{bookingId}/{roomDetailId}")
    public ResponseEntity bookingApprove(@PathVariable UUID bookingId, @PathVariable Long roomDetailId){
        HashMap<Object, Object> resultMap = new HashMap<>();

        // 중복체크

        Booking booking = bookingService.updateRoomDetailId(bookingId, roomDetailId);
        Map<String, Object> paymentResult = paymentService.nPayProgress(booking.getPaymentId());
        if(paymentResult.get("code").equals("Success")){
            bookingService.updatePaycheck(bookingId, "paid", booking.getPaymentId());
            resultMap.put("booking", booking);
        }
        resultMap.put("msg", "요청 성공");
        resultMap.put("paymentResult", paymentResult);
        return ResponseEntity.ok().body(resultMap);
    }


    //=============================================================================================
    //================================              del               =============================
    //=============================================================================================

    // 매니저가 호텔 삭제
    @DeleteMapping("/manager/myhotel/{hotelId}")
    public ResponseEntity delmyHotel(@PathVariable Long hotelId) {
        HashMap<Object, Object> resultMap = new HashMap<>();

        hotelService.delete(hotelId);

        resultMap.put("msg", "요청 성공");
        return ResponseEntity.ok().body(resultMap);
    }

    // 매니저가 호텔 사진 삭제
    @DeleteMapping("/manager/myhotelImg")
    public ResponseEntity delmyHotelImg(@RequestBody Map<String, String> params) {
        HashMap<Object, Object> resultMap = new HashMap<>();

        String imgid = params.get("himgId");
        Long himgId = Long.parseLong(imgid);
        hotelImgService.delete(himgId);

        resultMap.put("msg", "요청 성공");
        return ResponseEntity.ok().body(resultMap);
    }

    // 매니저가 객실 사진 삭제
    @DeleteMapping("/manager/myroomImg")
    public ResponseEntity delmyRoomImg(@RequestBody Map<String, String> params) {
        HashMap<Object, Object> resultMap = new HashMap<>();

        String imgid = params.get("rimgId");
        Long rimgId = Long.parseLong(imgid);
        roomImgService.delete(rimgId);

        resultMap.put("msg", "요청 성공");
        return ResponseEntity.ok().body(resultMap);
    }

    // 매니저가 room 삭제
    @DeleteMapping("/manager/myroom/{roomId}")
    public ResponseEntity delmyRoom(@PathVariable Long roomId) {
        HashMap<Object, Object> resultMap = new HashMap<>();

        roomService.delete(roomId);

        resultMap.put("msg", "요청 성공");
        return ResponseEntity.ok().body(resultMap);
    }

    // 매니저가 객실디테일 삭제
    @DeleteMapping("/manager/myroomdetail/{roomDetailId}")
    public ResponseEntity delmyRoomDetail(@PathVariable Long roomDetailId) {
        HashMap<Object, Object> resultMap = new HashMap<>();

        roomDetailService.delete(roomDetailId);

        resultMap.put("msg", "요청 성공");
        return ResponseEntity.ok().body(resultMap);
    }
//
//    // 매니저가 호텔 삭지
//    @DeleteMapping("/manager/myhotel/{hotelId}")
//    public ResponseEntity delmyHotel(@PathVariable Long hotelId) {
//        HashMap<Object, Object> resultMap = new HashMap<>();
//
//        roomService.deleteAllByHotelId(hotelId);
//        hotelService.delete(hotelId);
//
//        resultMap.put("msg", "요청 성공");
//        return ResponseEntity.ok().body(resultMap);
//    }
//
//    // 매니저가 객실 타입 삭제
//    @DeleteMapping("/manager/myroom/{roomId}")
//    public ResponseEntity delmyRoom(@PathVariable Long roomId) {
//        HashMap<Object, Object> resultMap = new HashMap<>();
//
//        roomService.delete(roomId);
//
//        resultMap.put("msg", "요청 성공");
//        return ResponseEntity.ok().body(resultMap);
//    }

}
