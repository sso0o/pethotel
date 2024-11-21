package com.example.pethotel.repository;

import com.example.pethotel.entity.HotelImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelImgRepository extends JpaRepository<HotelImg, Long> {

    // 호텔 아이디별 삭제


}
