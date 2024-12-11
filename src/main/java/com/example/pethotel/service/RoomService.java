package com.example.pethotel.service;

import com.example.pethotel.dto.AddRoomRequest;
import com.example.pethotel.dto.UpdateRoomRequest;
import com.example.pethotel.dto.hotel.SearchHotelRequest;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.Room;
import com.example.pethotel.exception.InvalidlValueException;
import com.example.pethotel.repository.RoomRepository;
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
    private final HotelService hotelService;

    @PersistenceContext
    private EntityManager entityManager;

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

//    public Page<Room> findSearchRoom(Long hotelId, String startDate, String endDate, int page, int size) {
//        Pageable pageable = PageRequest.of(page - 1, size);  // 페이지는 0부터 시작하므로, 1을 빼서 전달
//        return roomRepository.findBySearchRoom(pageable, hotelId, startDate, endDate);
//    }

//    public Page<Room> findSearchRoom(Long hotelId, String startDate, String endDate, int page, int size){
//    String sql = "WITH RECURSIVE search_date AS ( " +
//                "    SELECT ?1 AS startdate " + // 날짜 변수 1
//                "    UNION ALL " +
//                "    SELECT DATE_ADD(startdate, INTERVAL 1 DAY) " +
//                "    FROM search_date " +
//                "    WHERE DATE_ADD(startdate, INTERVAL 1 DAY) < ?2 " + // 날짜 변수 2
//                ") " +
//                "SELECT r.* " +
//                "FROM room r " +
//                "WHERE r.room_id NOT IN ( " +
//                "    SELECT b.room_id " +
//                "    FROM booking b " +
//                "    JOIN search_date ds ON b.start_date <= ds.startdate AND ds.startdate < b.end_date " +
//                "    WHERE b.payment_id IS NOT NULL " +
//                ") And r.hotel_id = ?3 ";
//        Pageable pageable = PageRequest.of(page - 1, size);
//
//        Query query = entityManager.createNativeQuery(sql, Room.class);
//        query.setParameter(1, startDate);
//        query.setParameter(2, endDate);
//        query.setParameter(3, hotelId);
//
//        List<Room> room = query.getResultList();
//
//
//        return new PageImpl<>(room, pageable, totalCount);
//    }
}
