package com.example.pethotel.repository;

import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.HotelFacility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HotelFacilityRepository extends JpaRepository<HotelFacility, UUID> {
    List<HotelFacility> findAllByHotel(Hotel hotel);
}
