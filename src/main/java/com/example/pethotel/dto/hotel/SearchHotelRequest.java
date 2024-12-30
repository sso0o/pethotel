package com.example.pethotel.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchHotelRequest {
    private String location;
    private String hotelType;
    private String checkIn;
    private String checkOut;
    private int room;
    private int guest;
    private int pet;
}
