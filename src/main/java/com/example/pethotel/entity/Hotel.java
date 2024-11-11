package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @Column(name = "hotel_type")
    private String hotelType;

    @Column(name = "hotel_add1")
    private String hotelAdd1;

    @Column(name = "hotel_add2")
    private String hotelAdd2;

    @Column(name = "hotel_phone")
    private String hotelPhone;

    @Column(name = "hotel_info")
    private String hotelInfo;

    @Column(name = "userid")
    private Long userId;

    @Builder
    public Hotel(String hotelName, String hotelType, String hotelAdd1, String hotelAdd2, String hotelPhone, String hotelInfo) {
        this.hotelName = hotelName;
        this.hotelType = hotelType;
        this.hotelAdd1 = hotelAdd1;
        this.hotelAdd2 = hotelAdd2;
        this.hotelPhone = hotelPhone;
        this.hotelInfo = hotelInfo;
    }

    public void update(String hotelName, String hotelType,
                       String hotelAdd1, String hotelAdd2,
                       String hotelPhone, String hotelInfo) {
        this.hotelName = hotelName;
        this.hotelType = hotelType;
        this.hotelAdd1 = hotelAdd1;
        this.hotelAdd2 = hotelAdd2;
        this.hotelPhone = hotelPhone;
        this.hotelInfo = hotelInfo;
    }

}

