package com.example.pethotel.service;

import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.RoomAmenity;
import com.example.pethotel.repository.RoomAmenityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomAmenityService {

    private final RoomAmenityRepository roomAmenityRepository;


    public RoomAmenity save(Room room, String code) {
        return roomAmenityRepository.save(RoomAmenity.builder()
                .room(room)
                .code(code)
                .build());
    }


    @Transactional
    public void deleteByRoom (Room room) {
        List<RoomAmenity> roomAmenities = roomAmenityRepository.findAllByRoom(room);
        roomAmenities.forEach(roomAmenity -> roomAmenityRepository.delete(roomAmenity));
    }
}

