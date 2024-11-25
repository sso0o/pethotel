package com.example.pethotel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference // 직렬화 제외
    private Hotel hotel;

    @Column(name = "himg_file")
    private String himgFile;

    @Column(name = "himg_url")
    private String himgUrl;



    @Builder
    public HotelImg(Hotel hotel, String himgFile, String himgUrl) {
        this.hotel = hotel;
        this.himgFile = himgFile;
        this.himgUrl = himgUrl;
    }

}
