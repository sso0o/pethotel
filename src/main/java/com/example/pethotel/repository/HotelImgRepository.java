package com.example.pethotel.repository;

import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.HotelImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelImgRepository extends JpaRepository<HotelImg, Long> {

    List<HotelImg> findByHotel(Hotel hotel);

    void deleteAllByHotel(Hotel hotel);
}
