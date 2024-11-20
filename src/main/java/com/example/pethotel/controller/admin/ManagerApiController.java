package com.example.pethotel.controller.admin;

import com.example.pethotel.dto.AddHotelImgRequest;
import com.example.pethotel.dto.AddHotelRequest;
import com.example.pethotel.dto.AddRoomImgRequest;
import com.example.pethotel.dto.AddRoomRequest;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.HotelImg;
import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.User;
import com.example.pethotel.service.*;
import com.example.pethotel.service.admin.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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



    //=============================================================================================
    //================================              post              =============================
    //=============================================================================================

    // 매니저가 호텔 저장
    @PostMapping("/manager/myhotel")
    public ResponseEntity saveHotel(
            @RequestParam("userId") Long userId,
            @RequestParam("hotelName") String hotelName,
            @RequestParam("hotelType") String hotelType,
            @RequestParam("postcode") String postcode,
            @RequestParam("address") String address,
            @RequestParam("detailAddress") String detailAddress,
            @RequestParam("extraAddress") String extraAddress,
            @RequestParam("hotelPhone") String hotelPhone,
            @RequestParam("hotelInfo") String hotelInfo,
            @RequestParam("hotelPhotos") MultipartFile[] hotelPhotos)  throws IOException {
        HashMap<Object, Object> resultMap = new HashMap<>();

        // 1. 호텔 저장
        AddHotelRequest req = new AddHotelRequest(hotelName, hotelType, postcode, address, detailAddress, extraAddress, hotelPhone, hotelInfo, userId);
        Hotel saveHotel = hotelService.save(req);

        // 파일 처리
        List<String> fileNames = fileService.saveFiles(hotelPhotos);

        // 2. 파일정보 저장
        for (String fileName : fileNames) {
            AddHotelImgRequest imgr = new AddHotelImgRequest(saveHotel, fileName);
            HotelImg hotelImg = hotelImgService.save(imgr);
        }
        resultMap.put("msg", "요청 성공");
        resultMap.put("hotel", saveHotel);

        return ResponseEntity.ok().body(resultMap);
    }


    // 매니저가 객실 저장
    @PostMapping("/manager/myroom")
    public ResponseEntity saveRoom(@RequestParam("hotelId") Long hotelId,
                                   @RequestParam("roomName") String roomName,
                                   @RequestParam("roomType") String roomType,
                                   @RequestParam("roomPrice") int roomPrice,
                                   @RequestParam("limitGuest") int limitGuest,
                                   @RequestParam("limitPet") int limitPet,
                                   @RequestParam("checkIn") String checkIn,
                                   @RequestParam("checkOut") String checkOut,
                                   @RequestParam("roomInfo") String roomInfo,
                                   @RequestParam("roomPhotos") MultipartFile[] roomPhotos) throws IOException {
        HashMap<Object, Object> resultMap = new HashMap<>();

        // 1. 객실 저장
        AddRoomRequest req = new AddRoomRequest(roomName, roomType, roomPrice, limitGuest, limitPet, checkIn, checkOut, roomInfo, hotelId);
        Room saveRoom = roomService.save(req);

        // 파일 처리
        List<String> fileNames = fileService.saveFiles(roomPhotos);

        // 2. 파일정보 저장
        for (String fileName : fileNames) {
            AddRoomImgRequest imgr = new AddRoomImgRequest(saveRoom, fileName); // Save for room
            roomImgService.save(imgr);
        }

        resultMap.put("msg", "요청 성공");
        resultMap.put("room", saveRoom);

        return ResponseEntity.ok().body(resultMap);

    }


    //=============================================================================================
    //================================              put               =============================
    //=============================================================================================
}
