package com.example.pethotel.service;

import com.example.pethotel.dto.hotel.SearchHotelRequest;
import com.example.pethotel.dto.manager.AddRoomRequest;
import com.example.pethotel.dto.manager.UpdateRoomRequest;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.Room;
import com.example.pethotel.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomImgRepository roomImgRepository;
    private final RoomDetailRepository roomDetailRepository;
    private final RoomAmenityRepository roomAmenityRepository;
    private final HotelRepository hotelRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // 객실 저장 요청
    public Room save(AddRoomRequest req) {
        Hotel hotel = hotelRepository.findById(req.getHotelId())
                .orElseThrow(() ->  new IllegalArgumentException("not found : "+req.getHotelId()));

        Room room = req.toEntity(hotel);
        return roomRepository.save(room);
    }

    // 객실 수정 요청
    @Transactional
    public Room update(Long roomId, UpdateRoomRequest req) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->  new IllegalArgumentException("not found : "+roomId));
        room.update(req.getRoomType(), req.getRoomName(),
                req.getRoomPrice(), req.getLimitGuest(), req.getLimitPet(),
                req.getCheckIn(), req.getCheckOut());
        return room;
    }

    public List<Room> findAllByHotelId(Long hotelId) {
        return roomRepository.findAllByHotelId(hotelId);
    }

    public Room findById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() ->  new IllegalArgumentException("객실을 찾을수 없습니다."));
    }




    public Page<Room> findBySearchOption(Long hotelId, int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size);  // 페이지는 0부터 시작하므로, 1을 빼서 전달
        return roomRepository.findBySearchOption(pageable, hotelId);
    }

    public Page<Room> findSearchRoom(Long hotelId, SearchHotelRequest request, int page, int size) {
        // Pageable 객체 생성 (무한 스크롤 페이징)
        Pageable pageable = PageRequest.of(page-1, size);
        // 예약되지 않은 방 조회
        return roomRepository.findBySearchRoom(pageable, hotelId, request.getGuest(), request.getPet(), request.getCheckIn(), request.getCheckOut());
    }

    @Transactional
    public void delete(Long roomId) {
        Room room = roomRepository.findById(roomId)
               .orElseThrow(() -> new IllegalArgumentException("not found : " + roomId));
        roomDetailRepository.deleteAllByRoom(room);
        roomImgRepository.deleteAllByRoom(room);
        roomAmenityRepository.deleteAllByRoom(room);

        roomRepository.delete(room);
    }

    @Transactional
    public void deleteByHotel(Hotel hotel) {
        List<Room> rooms = roomRepository.findAllByHotel(hotel);
        rooms.forEach(room -> {
            roomDetailRepository.deleteAllByRoom(room);
            roomImgRepository.deleteAllByRoom(room);
            roomRepository.delete(room);
        });
    }

    // 매니저가 예약관리 페이지때 씀
    public List<Room> findAllByHotel(Hotel hotel) {
        return roomRepository.findAllByHotel(hotel);
    }

}
