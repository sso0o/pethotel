package com.example.pethotel.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateRoomFeatureRequest {
    private String bedType;
    private String viewType;
    private String pool;
    private String roomCount;
    private String bathCount;
    private String balcony;
    private String kitchen;
}
