package com.example.pethotel.dto;

import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.HotelImg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddHotelImgRequest {

    private Hotel hotel;
    private String himgFile;

    public HotelImg toEntity() {
        return HotelImg.builder()
                .hotel(hotel)
                .himgFile(himgFile)
                .build();
    }
}
