package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "roomfeatures")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomFeatures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rf_id", updatable = false)
    private Long rfId;

    @Column(name = "code")
    private String code;

    @Column(name = "room_id")
    private Long roomId;

    @Builder
    public RoomFeatures(Long rfId, Long roomId, String code) {
        this.rfId = rfId;
        this.roomId = roomId;
        this.code = code;
    }
}
