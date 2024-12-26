package com.example.pethotel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonBackReference // 직렬화 제외
    private Room room;

    @Column(name = "bed_type")
    private String bedType;

    @Column(name = "view_type")
    private String viewType;

    @Column(name = "pool")
    private String pool;

    @Column(name = "room_count")
    private String roomCount;

    @Column(name = "bath_count")
    private String bathCount;

    @Column(name = "balcony")
    private String balcony;

    @Column(name = "kitchen")
    private String kitchen;



    @Builder
    public RoomFeature(Room room, String bedType, String viewType, String pool,
                       String roomCount, String bathCount, String balcony, String kitchen) {
        this.room = room;
        this.bedType = bedType;
        this.viewType = viewType;
        this.pool = pool;
        this.roomCount = roomCount;
        this.bathCount = bathCount;
        this.balcony = balcony;
        this.kitchen = kitchen;
    }
}
