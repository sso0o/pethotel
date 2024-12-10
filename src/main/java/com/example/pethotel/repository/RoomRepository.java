package com.example.pethotel.repository;

import com.example.pethotel.dto.hotel.SearchHotelRequest;
import com.example.pethotel.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "SELECT r from Room r where r.hotel.hotelId = :hotelId")
    List<Room> findAllByHotelId(@Param("hotelId") Long hotelId);


    @Query(value = "SELECT r " +
            "FROM Room r " +
            "where r.hotel.hotelId = :hotelId")
    Page<Room> findBySearchOption(Pageable pageable, @Param("hotelId") Long hotelId);

    @Query(value = "SELECT r " +
            "From Room r " +
            "where r.hotel.hotelId = :hotelId")
    Page<Room> findBySearch(Pageable pageable, @Param("hotelId") Long hotelId, SearchHotelRequest request);
    // 부킹테이블(시작일~종료일 + roomId)
    // +
    // room table
    // 조인 후 존재하지 않는 객실 아이디만
}
