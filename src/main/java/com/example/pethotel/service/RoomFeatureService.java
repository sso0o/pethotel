package com.example.pethotel.service;

import com.example.pethotel.dto.manager.AddRoomFeatureRequest;
import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.RoomFeature;
import com.example.pethotel.repository.RoomFeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomFeatureService {

    private final RoomFeatureRepository roomFeatureRepository;

    public RoomFeature save(AddRoomFeatureRequest req) {
        return roomFeatureRepository.save(req.toEntity);
    }

}
