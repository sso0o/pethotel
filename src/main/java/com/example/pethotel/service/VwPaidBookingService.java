package com.example.pethotel.service;

import com.example.pethotel.repository.VwPaidBookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VwPaidBookingService {
    private final VwPaidBookingRepository vwPaidBookingRepository;


    public Integer findByRoomDetailIdAndTargetDate(Long roomDetailId, String startDate, String endDate){
        return vwPaidBookingRepository.findByRoomDetailIdAndTargetDate(roomDetailId, startDate, endDate);
    }
}
