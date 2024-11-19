package com.example.pethotel.service;

import com.example.pethotel.dto.AddHotelImgRequest;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.HotelImg;
import com.example.pethotel.repository.HotelImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelImgService {

    private final HotelImgRepository hotelImgRepository;

    // 호텔 저장 요청
    public HotelImg save(AddHotelImgRequest req) {
        return hotelImgRepository.save(req.toEntity());
    }

}
