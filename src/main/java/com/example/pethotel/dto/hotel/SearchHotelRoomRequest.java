package com.example.pethotel.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchHotelRoomRequest {
    private Long hotelId;
    private String checkIn;
    private String checkOut;
    private int guest;
    private int pet;
    private int room;
}
