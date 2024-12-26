package com.example.pethotel.dto.manager;

import lombok.Getter;

@Getter
public class RoomFeatureResponse {
    private Long roomId;
    private String bedType;
    private String viewType;
    private String pool;
    private String roomCount;
    private String bathCount;
    private String balcony;
    private String kitchen;

    public RoomFeatureResponse(Long roomId, String bedType, String viewType, String pool, String roomCount,
                               String bathCount, String balcony, String kitchen) {
        this.roomId = roomId;
        this.bedType = bedType;
        this.viewType = viewType;
        this.pool = pool;
        this.roomCount = roomCount;
        this.bathCount = bathCount;
        this.balcony = balcony;
        this.kitchen = kitchen;
    }
}
