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
            "h.hotelId, h.hotelName, h.hotelInfo, MAX(r.roomPrice), MIN(r.roomPrice), COUNT(d)) " +
            "FROM Hotel h " +
            "INNER JOIN Room r ON h.hotelId = r.hotel.hotelId " +
            "INNER JOIN RoomDetail d on r.roomId = d.room.roomId " +
            "WHERE h.location like %:location% " +
            "AND r.limitGuest >= :guest " +
            "AND r.limitPet >= :pet " +
            "AND d.roomDetailId NOT IN (" +
            "   SELECT v.roomDetailId " +
            "   FROM VwPaidBooking v " +
            "   WHERE v.paymentId IS NOT NULL " +
            "   AND (v.startDate < :checkOut AND v.endDate > :checkIn)) " +
            "GROUP BY h.hotelId, h.hotelName, h.hotelInfo " +
            "having COUNT(d) >= :room ")
    Page<SearchHotelResponse> findBySearchOption(Pageable pageable,
                                                 @Param("location") String location,
                                                 @Param("guest") int guest,
                                                 @Param("pet") int pet,
                                                 @Param("checkIn") String checkIn,
                                                 @Param("checkOut") String checkOut,
                                                 @Param("room") int room);


    List<Hotel> findAllByLocation(String location);
}
