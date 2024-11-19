package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "hotelimg")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HotelImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "himg_id", updatable = false)
    private Long himgId;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "himg_file")
    private String himgFile;

    @Builder
    public HotelImg(Hotel hotel, String himgFile) {
        this.hotel = hotel;
        this.himgFile = himgFile;
    }
}
