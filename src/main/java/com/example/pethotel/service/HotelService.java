package com.example.pethotel.service;

import com.example.pethotel.dto.AddHotelRequest;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.repository.HotelImgRepository;
import com.example.pethotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelImgRepository hotelImgRepository;

    // 호텔 저장 요청
    public Hotel save(AddHotelRequest req) {
        return hotelRepository.save(req.toEntity());
    }


    public List<Hotel> findAllByUserId(Long userId) {
        return hotelRepository.findAllByUserId(userId);
    }

    public Hotel findById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }
}
