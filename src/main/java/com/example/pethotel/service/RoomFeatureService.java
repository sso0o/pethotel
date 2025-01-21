package com.example.pethotel.service;

import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.RoomFeature;
import com.example.pethotel.repository.RoomFeatureRepository;
import com.example.pethotel.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomFeatureService {

    private final RoomFeatureRepository roomFeatureRepository;
    private final RoomRepository roomRepository;


    public RoomFeature save(Room room, String featureType, String value) {
        return roomFeatureRepository.save(RoomFeature.builder()
                .room(room)
                .featureType(featureType)
                .value(value)
                .build());
    }

    @Transactional
    public void deleteByRoom(Room room) {
        roomFeatureRepository.deleteByRoom(room);
    }

    public List<RoomFeature> findByRoom(Room room) {
        return roomFeatureRepository.findByRoom(room);
    }



}
