package com.example.pethotel.repository;

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

//    @Query(value = "WITH RECURSIVE search_date AS ( " +
//            "    SELECT :startDate AS startdate " +
//            "    UNION ALL " +
//            "    SELECT DATE_ADD(startdate, INTERVAL 1 DAY) " +
//            "    FROM search_date " +
//            "    WHERE DATE_ADD(startdate, INTERVAL 1 DAY) < :endDate " +
//            ") " +
//            "SELECT r.* FROM room r " +
//            "WHERE r.room_id NOT IN ( " +
//            "    SELECT b.room_id " +
//            "    FROM booking b " +
//            "    JOIN search_date ds ON b.start_date <= ds.startdate AND ds.startdate < b.end_date " +
//            "    WHERE b.payment_id IS NOT NULL " +
//            ") " +
//            "AND r.hotel_id = :hotelId ",
//            nativeQuery = true)
//    Page<Room> findBySearchRoom(Pageable pageable, @Param("hotelId") Long hotelId,
//                                @Param("startDate")String startDate,
//                                @Param("endDate")String endDate);

    @Query("SELECT r " +
            "FROM Room r " +
            "WHERE r.hotel.hotelId = :hotelId " +
            "AND r.roomId NOT IN (" +
            "   SELECT b.roomId " +
            "   FROM Booking b " +
            "   WHERE b.paymentId IS NOT NULL " +
            "   AND (b.startDate < :endDate AND b.endDate > :startDate)) ")
    Page<Room> findBySearchRoom(Pageable pageable,
                                @Param("hotelId") Long hotelId,
                                @Param("startDate")String startDate,
                                @Param("endDate")String endDate);


}
