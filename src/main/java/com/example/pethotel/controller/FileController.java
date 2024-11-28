package com.example.pethotel.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Controller
public class FileController {

    // 이미지 파일 제공 엔드포인트
    @GetMapping("/manager/uploads/hotel/{fileName}")
    public ResponseEntity<?> getHotelImage(@PathVariable String fileName) {
        try {
            // 파일 경로 설정
            Path filePath = Paths.get("./uploads/hotel/" + fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .contentType(MediaType.IMAGE_JPEG) // 이미지 형식에 맞게 설정 (예: JPEG)
                        .body(resource);
            } else {
                throw new FileNotFoundException("파일을 찾을 수 없습니다. \n -> "+fileName);
            }
        } catch (IOException e) {
            // IOException 발생 시 JSON 형태로 에러 메시지 반환
            HashMap<String, String> errorMap = new HashMap<>();
            errorMap.put("msg", "파일을 처리하는 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);

        }
    }

    @GetMapping("/manager/myhotelroom/uploads/room/{fileName}")
    public ResponseEntity<?> getRoomImage(@PathVariable String fileName) {
        try {
            // 파일 경로 설정
            Path filePath = Paths.get("./uploads/room/" + fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .contentType(MediaType.IMAGE_JPEG) // 이미지 형식에 맞게 설정 (예: JPEG)
                        .body(resource);
            } else {
                throw new FileNotFoundException("파일을 찾을 수 없습니다. \n -> "+fileName);
            }
        } catch (IOException e) {
            // IOException 발생 시 JSON 형태로 에러 메시지 반환
            HashMap<String, String> errorMap = new HashMap<>();
            errorMap.put("msg", "파일을 처리하는 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);

        }
    }

    // 이미지 파일 제공 엔드포인트
    @GetMapping("/hotel/uploads/hotel/{fileName}")
    public ResponseEntity<?> getHotelImageForSlick(@PathVariable String fileName) {
        try {
            // 파일 경로 설정
            Path filePath = Paths.get("./uploads/hotel/" + fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .contentType(MediaType.IMAGE_JPEG) // 이미지 형식에 맞게 설정 (예: JPEG)
                        .body(resource);
            } else {
                throw new FileNotFoundException("파일을 찾을 수 없습니다. \n -> "+fileName);
            }
        } catch (IOException e) {
            // IOException 발생 시 JSON 형태로 에러 메시지 반환
            HashMap<String, String> errorMap = new HashMap<>();
            errorMap.put("msg", "파일을 처리하는 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);

        }
    }


//
//    @DeleteMapping("/manager/hotelImg/{fileName}")
//    public ResponseEntity<?> deleteImage(@PathVariable String fileName) {
//        try {
//            // 파일 경로 설정
//            Path filePath = Paths.get("./uploads/hotel/" + fileName);
//            File file = filePath.toFile();
//
//            if (file.exists()) {
//                if (file.delete()) {
//                    return ResponseEntity.ok().body("파일이 성공적으로 삭제되었습니다.");
//                } else {
//                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                            .body("파일 삭제 중 오류가 발생했습니다.");
//                }
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("파일을 찾을 수 없습니다.");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 삭제 중 오류가 발생했습니다.");
//        }
//    }

}
