package com.example.pethotel.controller.admin;

import com.example.pethotel.dto.AddHotelImgRequest;
import com.example.pethotel.dto.AddHotelRequest;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.HotelImg;
import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.User;
import com.example.pethotel.service.HotelImgService;
import com.example.pethotel.service.HotelService;
import com.example.pethotel.service.RoomService;
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

    @Value("${spring.file.upload-dir}")
    private String uploadDir;

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
    @GetMapping("/manager/myhotel/{userid}")
    public ResponseEntity getMyHotel(@PathVariable Long userid) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        List<Hotel> hotels = hotelService.findAllByUserId(userid);
        resultMap.put("hotels", hotels);
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

        // 디렉터리 확인 후 없으면 생성
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();  // 디렉터리 생성
        }

        // 2. 파일 처리
        if(hotelPhotos != null){
            for(MultipartFile file : hotelPhotos){
                if (!file.isEmpty()) {
                    String fileName = file.getOriginalFilename();
                    Path path = Paths.get(uploadDir ,fileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    // 파일정보 이미지테이블에 저장
                    AddHotelImgRequest imgr = new AddHotelImgRequest(saveHotel, fileName);
                    HotelImg hotelImg = hotelImgService.save(imgr);
                }
            }
        }
        resultMap.put("msg", "요청 성공");
        resultMap.put("hotel", saveHotel);

        return ResponseEntity.ok().body(resultMap);
    }


    //=============================================================================================
    //================================              put               =============================
    //=============================================================================================
}
