package com.example.pethotel.repository;

import com.example.pethotel.dto.manager.HotelBookingResponse;
import com.example.pethotel.dto.manager.HotelRequestResponse;
import com.example.pethotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query(value = "SELECT new com.example.pethotel.dto.manager.HotelBookingResponse( " +
            "b.roomDetailId, b.hotelId, b.startDate, b.endDate, b.bookingGuest, b.bookingPet, b.totalPrice, b.totalDate, b.paymentId, h.hotelName, r.roomType ) " +
            "FROM Booking b " +
            "left join Room r on b.roomDetailId = r.roomId " +
            "left join Hotel h on b.hotelId = h.hotelId " +
            "WHERE b.hotelId = :hotelId")
    List<HotelBookingResponse> findBookingResponseByHotelId(Long hotelId);

    @Query(value = "SELECT new com.example.pethotel.dto.manager.HotelRequestResponse( " +
            "b.paymentId, r.roomType, b.roomId, b.hotelId, b.startDate, b.endDate, b.totalPrice, b.totalDate, b.bookingGuest, b.bookingPet ) " +
            "FROM Booking b " +
            "left join Room r on b.roomId = r.roomId " +
            "WHERE b.hotelId = :hotelId " +
            "AND b.payChk = 'Success' " +
            "AND b.paymentId IS NOT NULL")
    List<HotelRequestResponse> findAllByHotelIdAndPayChk(Long hotelId);




    @Query(value = "SELECT d " +
            "FROM RoomDetail d " +
            "LEFT JOIN Booking b on d.roomDetailId = b.roomDetailId " +
            "WHERE b.roomId = :roomId " +
            "AND b.payChk = 'paid' " +
            "AND b.paymentId IS NOT NULL " +
            "AND b.startDate BETWEEN :startDate and :endDate ")
    List<Booking> findAllByRoomIdAndStartDateBetween(Long roomId, String startDate, String endDate);

}
