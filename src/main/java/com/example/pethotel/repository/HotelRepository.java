package com.example.pethotel.repository;

import com.example.pethotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findAllByUserId(Long userId);

    Optional<Hotel> findById(Long id);
}
