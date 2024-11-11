package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "roomamenities")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomAmenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ra_id", updatable = false)
    private Long raId;

    @Column(name = "code")
    private String code;

    @Column(name = "room_id")
    private Long roomId;

    @Builder
    public RoomAmenities(Long raId, Long roomId, String code) {
        this.raId = raId;
        this.roomId = roomId;
        this.code = code;
    }
}
