package com.example.pethotel.dto.hotel;

import lombok.Getter;

@Getter
public class SearchHotelResponse {
    private Long hotelId;
    private String hotelName;
    private String hotelInfo;
    private Integer maxPrice;
    private Integer minPrice;
    private Long roomCount;

    // Constructor
    public SearchHotelResponse(Long hotelId, String hotelName, String hotelInfo,
                             Integer maxPrice, Integer minPrice, Long roomCount) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelInfo = hotelInfo;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.roomCount = roomCount;
    }
}
