package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "hotel")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "location")
    private String location;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "address")
    private String address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "extra_address")
    private String extraAddress;

    @Column(name = "hotel_phone")
    private String hotelPhone;

    @Column(name = "hotel_info")
    private String hotelInfo;

    @Column(name = "userid")
    private Long userId;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<HotelImg> hotelPhotos;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Room> rooms;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<HotelFacility> hotelFacilities;

    @Builder
    public Hotel(String hotelName, String location,
                 String postcode, String address, String detailAddress, String extraAddress,
                 String hotelPhone, String hotelInfo, Long userId) {
        this.hotelName = hotelName;
        this.location = location;
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;
        this.hotelPhone = hotelPhone;
        this.hotelInfo = hotelInfo;
        this.userId = userId;
    }

    public void update(String hotelName, String location,
                       String postcode, String address, String detailAddress, String extraAddress,
                       String hotelPhone, String hotelInfo) {
        this.hotelName = hotelName;
        this.location = location;
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;
        this.hotelPhone = hotelPhone;
        this.hotelInfo = hotelInfo;
    }

    public int getLowestRoomPrice() {
        if (rooms == null || rooms.isEmpty()) {
            return 0; // rooms가 null이거나 비어있을 때 기본값 0 반환
        }
        return rooms.stream()
                .mapToInt(Room::getRoomPrice)
                .min()
                .orElse(0);
    }

}

