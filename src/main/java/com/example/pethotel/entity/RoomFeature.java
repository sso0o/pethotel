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

    @Column(name = "feature_type")
    private String featureType;

    @Column(name = "value")
    private String value;


    @Builder
    public RoomFeature(Room room, String featureType, String value) {
        this.room = room;
        this.featureType = featureType;
        this.value = value;
    }

    public void update(String featureType, String value) {
        this.featureType = featureType;
        this.value = value;
    }
}
