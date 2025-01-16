package com.example.pethotel.dto.hotel;

import lombok.Getter;

@Getter
public class SearchFilterHotelResponse {
    private Long hotelId;
    private String hotelName;
    private String hotelType;
    private String hotelInfo;
    private Integer maxPrice;
    private Integer minPrice;
//    private Long roomCount;
    private String hotelFacilities;

    // Constructor
    public SearchFilterHotelResponse(Long hotelId, String hotelName, String hotelType, String hotelInfo,
                               Integer maxPrice, Integer minPrice,
//                                     Long roomCount,
                                     String hotelFacilities) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelType = hotelType;
        this.hotelInfo = hotelInfo;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
//        this.roomCount = roomCount;
        this.hotelFacilities = hotelFacilities;
    }
}
