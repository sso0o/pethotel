package com.example.pethotel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "roomamenity")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomAmenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ra_id", updatable = false)
    private Long raId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonBackReference // 직렬화 제외
    private Room room;

    @Column(name = "code")
    private String code;

    @Column(name = "content")
    private String content;



    @Builder
    public RoomAmenity(Room room, String code, String content) {
        this.room = room;
        this.code = code;
        this.content = content;
    }
}
