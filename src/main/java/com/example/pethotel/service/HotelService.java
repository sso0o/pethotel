package com.example.pethotel.service;

import com.example.pethotel.entity.Hotel;
import com.example.pethotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public List<Hotel> findAllByUserId(Long userId) {
        return hotelRepository.findAllByUserId(userId);
    }
}
