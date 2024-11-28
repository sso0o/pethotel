package com.example.pethotel.repository;

import com.example.pethotel.entity.HotelImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelImgRepository extends JpaRepository<HotelImg, Long> {

    //List<HotelImg> findByHotelId(Long hotelId);


}
