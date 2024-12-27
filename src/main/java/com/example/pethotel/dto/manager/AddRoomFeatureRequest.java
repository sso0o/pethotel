package com.example.pethotel.dto.manager;

import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.RoomFeature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddRoomFeatureRequest {
    private Room room;
    private String bedType;
    private String viewType;
    private String pool;
    private String roomCount;
    private String bathCount;
    private String balcony;
    private String kitchen;

    public RoomFeature toEntity() {
        return RoomFeature.builder()
                .room(room)
                .bedType(bedType)
                .viewType(viewType)
                .pool(pool)
                .roomCount(roomCount)
                .bathCount(bathCount)
                .balcony(balcony)
                .kitchen(kitchen)
                .build();
    }
}
