package com.example.pethotel.repository;

import com.example.pethotel.dto.hotel.SearchHotelResponse;
import com.example.pethotel.entity.Hotel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findAllByUserId(Long userId);

    Optional<Hotel> findById(Long id);


    @Query(value = "SELECT new com.example.pethotel.dto.hotel.SearchHotelResponse(" +
            "h.hotelId, h.hotelName, h.hotelType, h.postcode, h.address, h.detailAddress, h.hotelPhone, h.hotelInfo, " +
            "MAX(r.roomPrice), MIN(r.roomPrice), AVG(r.roomPrice), COUNT(r.roomId)) " +
            "From Hotel h " +
            "left join Room r on h.hotelId = r.hotel.hotelId " +
            "group by h.hotelId ")
    Page<SearchHotelResponse> findBySearchOption(Pageable pageable);
//    @Param("location") String location,
//    @Param("checkIn") String checkIn,
//    @Param("checkOut") String checkOut,
//    @Param("room") int room,
//    @Param("guest") int guest,
//    @Param("pet") int pet

}
