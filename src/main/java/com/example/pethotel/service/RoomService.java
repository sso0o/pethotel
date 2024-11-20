package com.example.pethotel.service;

import com.example.pethotel.dto.AddRoomRequest;
import com.example.pethotel.entity.Room;
import com.example.pethotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    // 객실 저장 요청
    public Room save(AddRoomRequest req) {
        return roomRepository.save(req.toEntity());
    }

    public List<Room> findAllByHotelId(Long hotelId) {
        return roomRepository.findAllByHotelId(hotelId);
    }
}
