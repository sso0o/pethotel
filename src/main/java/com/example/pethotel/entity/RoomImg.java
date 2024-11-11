package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "roomimg")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomImg {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rimg_id", updatable = false)
    private Long rimgId;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "rimg_file")
    private String rimgFile;

    @Builder
    public RoomImg(Long rimgId, Long roomId, String rimgFile) {
        this.rimgId = rimgId;
        this.roomId = roomId;
        this.rimgFile = rimgFile;
    }


}
