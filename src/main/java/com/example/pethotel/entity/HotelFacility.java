package com.example.pethotel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "hotelfacility")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HotelFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hf_id", updatable = false)
    private Long hfId;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference // 직렬화 제외
    private Hotel hotel;

    @Column(name = "code")
    private String code;

    @Column(name = "content")
    private String content;

    @Builder
    public HotelFacility(Hotel hotel, String code, String content) {
        this.hotel = hotel;
        this.code = code;
        this.content = content;
    }
}
