package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "roomdetail")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class RoomDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomdetail_id")
    private Long roomDetailId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "room_name")
    private String roomName;

    @Builder
    public RoomDetail (Room room, String roomName) {
        this.room = room;
        this.roomName = roomName;
    }

    public void update(String roomName){
        this.roomName = roomName;
    }

}
