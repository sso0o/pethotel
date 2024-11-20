package com.example.pethotel.repository;

import com.example.pethotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "SELECT r from Room r where r.hotel.hotelId = :hotelId")
    List<Room> findAllByHotelId(@Param("hotelId") Long hotelId);
}
