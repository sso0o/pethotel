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

    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "code")
    private String code;

    @Column(name = "content")
    private String content;

    @Builder
    public HotelFacilities(Long hotelId, String code, String content) {
        this.hotelId = hotelId;
        this.code = code;
        this.content = content;
    }
}
