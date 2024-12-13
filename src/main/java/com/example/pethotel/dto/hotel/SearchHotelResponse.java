package com.example.pethotel.dto.hotel;

import lombok.Getter;

@Getter
public class SearchHotelResponse {
    private Long hotelId;
    private String hotelName;
    private String hotelType;
    private String postcode;
    private String address;
    private String detailAddress;
    private String hotelPhone;
    private String hotelInfo;
    private Integer maxPrice;
    private Integer minPrice;
    private Double avgPrice;
    private Long roomCount;

    // Constructor
    public SearchHotelResponse(Long hotelId, String hotelName, String hotelType, String postcode, String address,
                             String detailAddress, String hotelPhone, String hotelInfo,
                             Integer maxPrice, Integer minPrice, Double avgPrice, Long roomCount) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelType = hotelType;
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.hotelPhone = hotelPhone;
        this.hotelInfo = hotelInfo;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.avgPrice = avgPrice;
        this.roomCount = roomCount;
    }
}
