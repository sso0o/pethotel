package com.example.pethotel.dto.manager;

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
    private String himgUrl;

    public HotelImg toEntity() {
        return HotelImg.builder()
                .hotel(hotel)
                .himgFile(himgFile)
                .himgUrl(himgUrl)
                .build();
    }
}
