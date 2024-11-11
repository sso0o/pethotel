package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "hotelfacilities")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HotelFacilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hf_id", updatable = false)
    private Long hfId;

    @Column(name = "code")
    private String code;

    @Column(name = "hotel_id")
    private Long hotelId;

    @Builder
    public HotelFacilities(Long hfId, Long hotelId, String code) {
        this.hfId = hfId;
        this.hotelId = hotelId;
        this.code = code;
    }
}
