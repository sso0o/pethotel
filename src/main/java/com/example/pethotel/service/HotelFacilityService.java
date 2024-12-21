package com.example.pethotel.service;

import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.HotelFacility;
import com.example.pethotel.repository.HotelFacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelFacilityService {

    private final HotelFacilityRepository hotelFacilityRepository;

    public HotelFacility save(Hotel hotel, String code) {
        return hotelFacilityRepository.save(HotelFacility.builder()
                .hotel(hotel)
                .code(code)
                .build());
    }
}
