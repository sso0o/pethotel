package com.example.pethotel.controller.manager;


import com.example.pethotel.dto.*;
import com.example.pethotel.dto.manager.HotelBookingResponse;
import com.example.pethotel.entity.*;
import com.example.pethotel.exception.InvalidlValueException;
import com.example.pethotel.service.*;
import com.example.pethotel.service.admin.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/manager/mybooking/{hotelId}")
    public ResponseEntity getMyBooking(@PathVariable Long hotelId) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        List<HotelBookingResponse> bookings = bookingService.findBookingByHotelId(hotelId);
        resultMap.put("bookings", bookings);
        return ResponseEntity.ok().body(resultMap);
    }





    //=============================================================================================
    //================================              post              =============================
    //=============================================================================================

    // 매니저가 호텔 저장
    @PostMapping("/manager/myhotel")
    public ResponseEntity saveHotel(@RequestBody AddHotelRequest request) throws Exception {
        HashMap<Object, Object> resultMap = new HashMap<>();

        // 필수 항목들 체크
        commonService.checkRequiredField(request.getHotelName(), "호텔 이름은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getLocation(), "호텔 지역은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getPostcode(), "호텔 우편번호는 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getAddress(), "호텔 주소는 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getHotelPhone(), "호텔 번호는 필수 입력 항목입니다.");



        Hotel saveHotel = hotelService.save(request);
        resultMap.put("hotel", saveHotel);
        resultMap.put("msg", "요청 성공");
        return ResponseEntity.ok().body(resultMap);
    }

    // 매니저가 객실 저장
    @PostMapping("/manager/myroom")
    public ResponseEntity saveRoom(@RequestBody AddRoomRequest request) throws Exception {
        HashMap<Object, Object> resultMap = new HashMap<>();

        // 필수 항목들 체크
        commonService.checkRequiredField(request.getRoomName(), "객실 이름은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getRoomPrice(), "객실 가격은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getLimitGuest(), "최대 인원은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getLimitPet(), "최대 펫 수는 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getCheckIn(), "체크인 시간은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getCheckOut(), "체크아웃 시간은 필수 입력 항목입니다.");

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




    //=============================================================================================
    //================================              put               =============================
    //=============================================================================================

    // 매니저가 호텔 수정
    @PutMapping("/manager/myhotel/{hotelId}")
    public ResponseEntity updateHotel(@PathVariable Long hotelId, @RequestBody UpdateHotelRequest request) throws Exception {
        HashMap<Object, Object> resultMap = new HashMap<>();

        // 필수 항목들 체크
        commonService.checkRequiredField(request.getHotelName(), "호텔 이름은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getLocation(), "호텔 지역은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getPostcode(), "호텔 우편번호는 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getAddress(), "호텔 주소는 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getHotelPhone(), "호텔 번호는 필수 입력 항목입니다.");


        Hotel updateHotel = hotelService.update(hotelId, request);
        resultMap.put("msg", "요청 성공");
        resultMap.put("hotel", updateHotel);

        return ResponseEntity.ok().body(resultMap);
    }

    // 매니저가 객실 수정
    @PutMapping("/manager/myroom/{roomId}")
    public ResponseEntity updateRoom(@PathVariable Long roomId, @RequestBody UpdateRoomRequest request) throws Exception {
        HashMap<Object, Object> resultMap = new HashMap<>();

        // 필수 항목들 체크
        commonService.checkRequiredField(request.getRoomName(), "객실 이름은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getRoomPrice(), "객실 가격은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getLimitGuest(), "최대 인원은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getLimitPet(), "최대 펫 수는 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getCheckIn(), "체크인 시간은 필수 입력 항목입니다.");
        commonService.checkRequiredField(request.getCheckOut(), "체크아웃 시간은 필수 입력 항목입니다.");

        if (request.getCheckOut() == null){
            throw new InvalidlValueException("가격 입력");
        }

        Room updateRoom = roomService.update(roomId, request);
        resultMap.put("msg", "요청 성공");
        resultMap.put("room", updateRoom);
        return ResponseEntity.ok().body(resultMap);
    }



    //=============================================================================================
    //================================              del               =============================
    //=============================================================================================

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

}
