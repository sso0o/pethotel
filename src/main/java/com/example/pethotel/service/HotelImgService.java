package com.example.pethotel.service;

import com.example.pethotel.dto.AddHotelImgRequest;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.HotelImg;
import com.example.pethotel.repository.HotelImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelImgService {

    private final HotelImgRepository hotelImgRepository;

    // 호텔 사진 저장 요청
    public HotelImg save(AddHotelImgRequest req) {
        return hotelImgRepository.save(req.toEntity());
    }

    // 아이디로 호텔 사진 삭제
    public void delete(Long himgId) {
        HotelImg hotelImg = hotelImgRepository.findById(himgId)
                .orElseThrow(() ->  new IllegalArgumentException("호텔 사진을 찾을 수 없습니다"));
        Path filePath = Paths.get(hotelImg.getHimgUrl());
        File file = filePath.toFile();
        if (file.exists()) {
            file.delete();
        }
        hotelImgRepository.deleteById(himgId);
    }

    // 호텔 아이디로 이미지 가져오기
    public List<HotelImg> findByHotel(Hotel hotel) {
        return hotelImgRepository.findByHotel(hotel);
    }

}
