package com.example.pethotel.controller.admin;

import com.example.pethotel.dto.*;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.HotelImg;
import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.User;
import com.example.pethotel.exception.InvalidlValueException;
import com.example.pethotel.service.*;
import com.example.pethotel.service.admin.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ManagerApiController {


    private final ManagerService managerService;
    private final HotelService hotelService;
    private final HotelImgService hotelImgService;
    private final RoomService roomService;
    private final RoomImgService roomImgService;


    private final CommonService commonService;
    private final FileService fileService;


    //=============================================================================================
    //================================              get               =============================
    //=============================================================================================

    // 매니저 목록 조회
    @GetMapping("/admin/manager")
    public ResponseEntity managerList(){
        HashMap<Object, Object> resultMap = new HashMap<>();

        List<User> managers = managerService.findByUserroleAndUserstatus("MANAGER", "Y");
        resultMap.put("managers", managers);
        return ResponseEntity.ok().body(resultMap);
    }

//    // 매니저 별 호텔 목록 조회
//    @GetMapping("/admin/hotel/{userid}")
//    public ResponseEntity hotelListByManager(@PathVariable Long userid) {
//        HashMap<Object, Object> resultMap = new HashMap<>();
//        List<Hotel> hotels = hotelService.findAllByUserId(userid);
//        resultMap.put("hotels", hotels);
//        return ResponseEntity.ok().body(resultMap);
//
//    }

    // 호텔아이디 별 호텔 목록 조회
    @GetMapping("/admin/room/{hotelid}")
    public ResponseEntity roomListByHotel(@PathVariable Long hotelid) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        List<Room> rooms = roomService.findAllByHotelId(hotelid);
        resultMap.put("rooms", rooms);
        return ResponseEntity.ok().body(resultMap);

    }

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



    //=============================================================================================
    //================================              post              =============================
    //=============================================================================================



    // 매니저가 호텔 저장
    @PostMapping("/manager/myhotel")
    public ResponseEntity saveHotel(@RequestBody AddHotelRequest request) throws Exception {
        HashMap<Object, Object> resultMap = new HashMap<>();

        // 필수 항목들 체크
        commonService.checkRequiredField(request.getHotelName(), "호텔 이름은 필수 입력 항목입니다.");
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

        System.out.println(hotelPhotos.length);

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


    //=============================================================================================
    //================================              put               =============================
    //=============================================================================================

    // 매니저가 호텔 수정
    @PutMapping("/manager/myhotel/{hotelId}")
    public ResponseEntity updateHotel(@PathVariable Long hotelId, @RequestBody UpdateHotelRequest request) throws Exception {
        HashMap<Object, Object> resultMap = new HashMap<>();

        // 필수 항목들 체크
        commonService.checkRequiredField(request.getHotelName(), "호텔 이름은 필수 입력 항목입니다.");
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

}

//    // 매니저가 호텔 저장
//    @PostMapping("/manager/myhotel")
//    public ResponseEntity saveHotel(
//            @RequestParam("userId") Long userId,
//            @RequestParam("hotelName") String hotelName,
//            @RequestParam("hotelType") String hotelType,
//            @RequestParam("postcode") String postcode,
//            @RequestParam("address") String address,
//            @RequestParam("detailAddress") String detailAddress,
//            @RequestParam("extraAddress") String extraAddress,
//            @RequestParam("hotelPhone") String hotelPhone,
//            @RequestParam("hotelInfo") String hotelInfo,
//            @RequestParam(value = "hotelPhotos", required = false) MultipartFile[] hotelPhotos)  throws IOException {
//        HashMap<Object, Object> resultMap = new HashMap<>();
//
//        // 데이터 확인
//
//        // 1. 호텔 저장
//        AddHotelRequest req = new AddHotelRequest(hotelName, hotelType, postcode, address, detailAddress, extraAddress, hotelPhone, hotelInfo, userId);
//        Hotel saveHotel = hotelService.save(req);
//
//        // 파일이 존재하면 처리
//        if (hotelPhotos != null && hotelPhotos.length > 0) {
//            String uploadUrl = "./uploads/hotel";
//
//            List<String> fileNames = fileService.saveFiles(uploadUrl,hotelPhotos);
//
//            for (String fileName : fileNames) {
//                AddHotelImgRequest imgr = new AddHotelImgRequest(saveHotel, fileName, uploadUrl+"/"+fileName);
//                HotelImg hotelImg = hotelImgService.save(imgr);
//            }
//        }
//
//        resultMap.put("msg", "요청 성공");
//        resultMap.put("hotel", saveHotel);
//
//        return ResponseEntity.ok().body(resultMap);
//    }





// 매니저가 객실 저장

//    public ResponseEntity saveRoom(@RequestParam("hotelId") Long hotelId,
//                                   @RequestParam("roomName") String roomName,
//                                   @RequestParam("roomType") String roomType,
//                                   @RequestParam("roomPrice") int roomPrice,
//                                   @RequestParam("limitGuest") int limitGuest,
//                                   @RequestParam("limitPet") int limitPet,
//                                   @RequestParam("checkIn") String checkIn,
//                                   @RequestParam("checkOut") String checkOut,
//                                   @RequestParam("roomInfo") String roomInfo,
//                                   @RequestParam(value = "roomPhotos", required = false) MultipartFile[] roomPhotos) throws IOException {
//        HashMap<Object, Object> resultMap = new HashMap<>();
//
//
//
//        Hotel hotel = hotelService.findById(hotelId);
//        // 1. 객실 저장
//        AddRoomRequest req = new AddRoomRequest(roomName, roomType, roomPrice, limitGuest, limitPet, checkIn, checkOut, roomInfo, hotel);
//        Room saveRoom = roomService.save(req);
//
//
//        // 파일이 존재하면 처리
//        if (roomPhotos != null && roomPhotos.length > 0) {
//            String uploadUrl = "./uploads/room";
//            List<String> fileNames = fileService.saveFiles(uploadUrl, roomPhotos);
//
//            for (String fileName : fileNames) {
//                AddRoomImgRequest imgr = new AddRoomImgRequest(saveRoom, fileName); // Save for room
//                roomImgService.save(imgr);
//            }
//        }
//
//        resultMap.put("msg", "요청 성공");
//        resultMap.put("room", saveRoom);
//
//        return ResponseEntity.ok().body(resultMap);
//
//    }
