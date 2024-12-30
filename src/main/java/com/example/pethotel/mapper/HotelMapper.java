package com.example.pethotel.mapper;

import com.example.pethotel.entity.Hotel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelMapper {

    List<Hotel> findAll();
}
