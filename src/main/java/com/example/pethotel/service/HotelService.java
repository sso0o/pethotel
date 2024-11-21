package com.example.pethotel.service;

import com.example.pethotel.dto.AddHotelRequest;
import com.example.pethotel.dto.UpdateHotelRequest;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.repository.HotelImgRepository;
import com.example.pethotel.repository.HotelRepository;
import jakarta.transaction.Transactional;
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

    // 호텔 수정 요청
    @Transactional
    public Hotel update(Long hotelId, UpdateHotelRequest req) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() ->  new IllegalArgumentException("not found : "+hotelId));
        hotel.update(req.getHotelName(), req.getHotelType(),
                req.getPostcode(), req.getAddress(),
                req.getDetailAddress(), req.getExtraAddress(),
                req.getHotelPhone(), req.getHotelInfo());
        return hotel;
    }


    public List<Hotel> findAllByUserId(Long userId) {
        return hotelRepository.findAllByUserId(userId);
    }

    public Hotel findById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }
}
