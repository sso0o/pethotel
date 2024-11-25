package com.example.pethotel.service;

import com.example.pethotel.dto.AddRoomImgRequest;
import com.example.pethotel.entity.RoomImg;
import com.example.pethotel.repository.RoomImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class RoomImgService {
    private final RoomImgRepository roomImgRepository;

    // 객실 이미지 저장요청
    public RoomImg save(AddRoomImgRequest req) {
        return roomImgRepository.save(req.toEntity());
    }

    // 아이디로 사진 삭제
    public void delete(Long  rimgId) {
        RoomImg roomImg = roomImgRepository.findById(rimgId)
                .orElseThrow(() ->  new IllegalArgumentException("사진을 찾을 수 없습니다"));
        Path filePath = Paths.get(roomImg.getRimgUrl());
        File file = filePath.toFile();
        if (file.exists()) {
            file.delete();
        }
        roomImgRepository.delete(roomImg);
    }
}
