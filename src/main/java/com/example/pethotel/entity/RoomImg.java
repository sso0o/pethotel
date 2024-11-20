package com.example.pethotel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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


    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonBackReference // 직렬화 제외
    private Room room;

    @Column(name = "rimg_file")
    private String rimgFile;

    @Builder
    public RoomImg(Room room, String rimgFile) {
        this.room = room;
        this.rimgFile = rimgFile;
    }


}
