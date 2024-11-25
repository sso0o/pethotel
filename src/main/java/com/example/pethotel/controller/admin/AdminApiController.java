package com.example.pethotel.controller.admin;

import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.User;
import com.example.pethotel.service.*;
import com.example.pethotel.service.admin.CommonCodeService;
import com.example.pethotel.service.admin.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class AdminApiController {

    private final ManagerService managerService;
    private final HotelService hotelService;
    private final HotelImgService hotelImgService;
    private final RoomService roomService;
    private final RoomImgService roomImgService;


    private final CommonService commonService;
    private final FileService fileService;

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

    // 호텔아이디 별 객실 목록 조회
    @GetMapping("/admin/room/{hotelid}")
    public ResponseEntity roomListByHotel(@PathVariable Long hotelid) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        List<Room> rooms = roomService.findAllByHotelId(hotelid);
        resultMap.put("rooms", rooms);
        return ResponseEntity.ok().body(resultMap);

    }





}
