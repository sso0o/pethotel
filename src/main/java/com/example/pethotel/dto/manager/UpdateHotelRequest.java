package com.example.pethotel.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateHotelRequest {
    private String hotelName;
    private String hotelType;
    private String location;
    private String postcode;
    private String address;
    private String detailAddress;
    private String extraAddress;
    private String hotelPhone;
    private String hotelInfo;
    private String hotelFacilities;
}
