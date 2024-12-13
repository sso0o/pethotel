package com.example.pethotel.service;

import com.example.pethotel.dto.manager.AddRoomDetailRequest;
import com.example.pethotel.entity.RoomDetail;
import com.example.pethotel.repository.RoomDetailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomDetailService {

    private final RoomDetailRepository roomDetailRepository;

    public RoomDetail save(AddRoomDetailRequest req) {
        return roomDetailRepository.save(req.toEntity());
    }

    @Transactional
    public RoomDetail update(Long roomDetailId, String roomName) {
        RoomDetail roomDetail = roomDetailRepository.findById(roomDetailId)
               .orElseThrow(() -> new IllegalArgumentException("not found : " + roomDetailId));
        roomDetail.update(roomName);
        return roomDetail;

    }

    public List<RoomDetail> findAllByRoomId(Long roomId) {
        return roomDetailRepository.findAllByRoomId(roomId);
    }

    public void delete(Long roomDetailId) {
        RoomDetail roomDetail = roomDetailRepository.findById(roomDetailId)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + roomDetailId));
        roomDetailRepository.delete(roomDetail);
    }


}
