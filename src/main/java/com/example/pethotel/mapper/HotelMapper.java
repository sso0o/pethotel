package com.example.pethotel.mapper;

import com.example.pethotel.dto.hotel.SearchHotelResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotelMapper {

//    // 페이징 처리된 호텔 목록 조회
//    List<SearchHotelResponse> findBySearchFilter(SearchHotelRequest request, Criteria criteria);

    List<SearchHotelResponse> findBySearchFilter(
            @Param("location") String location,
            @Param("hotelType") String hotelType,
            @Param("guest") int guest,
            @Param("pet") int pet,
            @Param("room") int room,
            @Param("size") int size,
            @Param("skip") int skip);
}

