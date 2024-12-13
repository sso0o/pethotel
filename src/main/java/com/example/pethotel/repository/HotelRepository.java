package com.example.pethotel.repository;

import com.example.pethotel.dto.hotel.SearchHotelResponse;
import com.example.pethotel.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findAllByUserId(Long userId);

    Optional<Hotel> findById(Long hotelId);


    @Query(value = "SELECT new com.example.pethotel.dto.hotel.SearchHotelResponse(" +
            "h.hotelId, h.hotelName, h.hotelType, h.postcode, h.address, h.detailAddress, h.hotelPhone, h.hotelInfo, " +
            "MAX(r.roomPrice), MIN(r.roomPrice), AVG(r.roomPrice), COUNT(r.roomId)) " +
            "FROM Hotel h " +
            "INNER JOIN Room r ON h.hotelId = r.hotel.hotelId " +
            "WHERE h.location = :location " +
            "AND r.limitGuest >= :guest " +
            "AND r.limitPet >= :pet " +
            "AND r.roomId NOT IN (" +
            "   SELECT b.roomId " +
            "   FROM Booking b " +
            "   WHERE b.paymentId IS NOT NULL " +
            "   AND (b.startDate < :checkOut AND b.endDate > :checkIn)) " +
            "GROUP BY h.hotelId")
    Page<SearchHotelResponse> findBySearchOption(Pageable pageable,
                                                 @Param("location") String location,
                                                 @Param("guest") int guest,
                                                 @Param("pet") int pet,
                                                 @Param("checkIn") String checkIn,
                                                 @Param("checkOut") String checkOut);


}
