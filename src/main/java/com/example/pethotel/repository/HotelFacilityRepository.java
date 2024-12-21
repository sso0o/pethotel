package com.example.pethotel.repository;

import com.example.pethotel.entity.HotelFacility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelFacilityRepository extends JpaRepository<HotelFacility, UUID> {
}
