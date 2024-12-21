package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "roomfeature")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rf_id", updatable = false)
    private Long rfId;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "code")
    private String code;

    @Column(name = "content")
    private String content;



    @Builder
    public RoomFeature(Long roomId, String code, String content) {
        this.roomId = roomId;
        this.code = code;
        this.content = content;
    }
}
