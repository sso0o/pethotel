package com.example.pethotel.mapper;

import com.example.pethotel.dto.hotel.SearchHotelRoomResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomMapper {
    List<SearchHotelRoomResponse> findAvailableRoomList(
            @Param("hotelId") Long hotelId,
            @Param("guest") int guest,
            @Param("pet") int pet,
            @Param("checkIn") String checkIn,
            @Param("checkOut") String checkOut,
            @Param("room") int room,
            @Param("size") int size,
            @Param("skip") int skip);
}

