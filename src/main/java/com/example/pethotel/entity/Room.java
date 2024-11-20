package com.example.pethotel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference // 직렬화 제외
    private Hotel hotel;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "room_price")
    private int roomPrice;

    @Column(name = "limit_guest")
    private int limitGuest;

    @Column(name = "limit_pet")
    private int limitPet;

    @Column(name = "room_info")
    private String roomInfo;

    @Column(name = "check_in")
    private String checkIn;

    @Column(name = "check_out")
    private String checkOut;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<RoomImg> roomPhotos;

    @Builder
    public Room (Long hotelId, String roomName, String roomType,
                 int roomPrice, int limitGuest, int limitPet,
                 String roomInfo, String checkIn, String checkOut) {
        this.roomName = roomName;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.limitGuest = limitGuest;
        this.limitPet = limitPet;
        this.roomInfo = roomInfo;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }


    public void update(String roomName, String roomType,
                       int roomPrice, int limitGuest, int limitPet,
                       String roomInfo, String checkIn, String checkOut) {
        this.roomName = roomName;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.limitGuest = limitGuest;
        this.limitPet = limitPet;
        this.roomInfo = roomInfo;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
