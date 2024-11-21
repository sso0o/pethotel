package com.example.pethotel.service;

import com.example.pethotel.dto.AddRoomRequest;
import com.example.pethotel.dto.UpdateRoomRequest;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.Room;
import com.example.pethotel.exception.InvalidlValueException;
import com.example.pethotel.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelService hotelService;  //

    // 객실 저장 요청
    public Room save(AddRoomRequest req) {
        Hotel hotel = hotelService.findById(req.getHotelId());
        if (hotel == null) {
            throw new InvalidlValueException("유효하지 않은 호텔입니다.");
        }
        Room room = req.toEntity(hotel);
        return roomRepository.save(room);
    }

    // 객실 수정 요청
    @Transactional
    public Room update(Long roomId, UpdateRoomRequest req) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->  new IllegalArgumentException("not found : "+roomId));
        room.update(req.getRoomName(), req.getRoomType(), req.getRoomPrice(),
                req.getLimitGuest(), req.getLimitPet(),
                req.getCheckIn(), req.getCheckOut(), req.getRoomInfo());
        return room;
    }

    public List<Room> findAllByHotelId(Long hotelId) {
        return roomRepository.findAllByHotelId(hotelId);
    }
}
