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

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "code")
    private String code;

    @Column(name = "content")
    private String content;



    @Builder
    public RoomAmenities(Long roomId, String code, String content) {
        this.roomId = roomId;
        this.code = code;
        this.content = content;
    }
}
