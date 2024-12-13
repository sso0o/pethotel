package com.example.pethotel.repository;

import com.example.pethotel.entity.Hotel;
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
    List<Room> findAllByHotel(Hotel hotel);

    @Query(value = "SELECT r " +
            "FROM Room r " +
            "where r.hotel.hotelId = :hotelId")
    Page<Room> findBySearchOption(Pageable pageable, @Param("hotelId") Long hotelId);


    // 고객이 검색할때 조회하는 객실
    @Query("SELECT r " +
            "FROM Room r " +
            "WHERE r.hotel.hotelId = :hotelId " +
            "AND r.limitGuest >= :guest " +
            "AND r.limitPet >= :pet " +
            "AND r.roomId NOT IN (" +
            "   SELECT b.roomId " +
            "   FROM Booking b " +
            "   WHERE b.paymentId IS NOT NULL " +
            "   AND (b.startDate < :checkOut AND b.endDate > :checkIn)) ")
    Page<Room> findBySearchRoom(Pageable pageable,
                                @Param("hotelId") Long hotelId,
                                @Param("guest") int guest,
                                @Param("pet") int pet,
                                @Param("checkIn") String checkIn,
                                @Param("checkOut") String checkOut);

}
