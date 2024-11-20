package com.example.pethotel.service;

import com.example.pethotel.dto.AddRoomImgRequest;
import com.example.pethotel.entity.RoomImg;
import com.example.pethotel.repository.RoomImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomImgService {
    private final RoomImgRepository roomImgRepository;

    public RoomImg save(AddRoomImgRequest req) {
        return roomImgRepository.save(req.toEntity());
    }
}
