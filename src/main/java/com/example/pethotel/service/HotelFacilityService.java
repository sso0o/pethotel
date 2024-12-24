package com.example.pethotel.service;

import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.HotelFacility;
import com.example.pethotel.repository.HotelFacilityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional
    public void deleteByHotel(Hotel hotel) {
        List<HotelFacility> hotelFacilities = hotelFacilityRepository.findAllByHotel(hotel);
        hotelFacilities.forEach(hotelFacility -> hotelFacilityRepository.delete(hotelFacility));
    }
}
