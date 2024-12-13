package com.example.pethotel.repository;

import com.example.pethotel.dto.manager.HotelBookingResponse;
import com.example.pethotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query(value = "SELECT new com.example.pethotel.dto.manager.HotelBookingResponse( " +
            "b.roomId, b.hotelId, b.startDate, b.endDate, b.bookingGuest, b.bookingPet, b.totalPrice, b.totalDate, b.paymentId, h.hotelName, r.roomType ) " +
            "FROM Booking b " +
            "left join Room r on b.roomId = r.roomId " +
            "left join Hotel h on b.hotelId = h.hotelId " +
            "WHERE b.hotelId = :hotelId")
    List<HotelBookingResponse> findAllByHotelId(Long hotelId);
}
