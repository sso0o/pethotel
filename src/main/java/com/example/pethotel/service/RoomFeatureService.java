package com.example.pethotel.service;

import com.example.pethotel.dto.manager.AddRoomFeatureRequest;
import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.RoomFeature;
import com.example.pethotel.repository.RoomFeatureRepository;
import com.example.pethotel.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomFeatureService {

    private final RoomFeatureRepository roomFeatureRepository;
    private final RoomRepository roomRepository;

    public RoomFeature save(AddRoomFeatureRequest req) {
        return roomFeatureRepository.save(req.toEntity());
    }

    @Transactional
    public void deleteByRoom(Room room) {
        roomFeatureRepository.deleteByRoom(room);
    }

    public RoomFeature findByRoom(Room room) {
        return roomFeatureRepository.findByRoom(room);
    }



}
