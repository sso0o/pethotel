package com.example.pethotel.dto;

import com.example.pethotel.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddHotelRequest {

    private String hotelName;
    private String hotelType;
    private String postcode;
    private String address;
    private String detailAddress;
    private String extraAddress;
    private String hotelPhone;
    private String hotelInfo;
    private Long userId;


    public Hotel toEntity() {
        return Hotel.builder()
                .hotelName(hotelName)
                .hotelType(hotelType)
                .postcode(postcode)
                .address(address)
                .detailAddress(detailAddress)
                .extraAddress(extraAddress)
                .hotelPhone(hotelPhone)
                .hotelInfo(hotelInfo)
                .userId(userId)
                .build();
    }

}
